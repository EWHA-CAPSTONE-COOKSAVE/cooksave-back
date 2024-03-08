package CookSave.CookSaveback.History.service;

import CookSave.CookSaveback.History.domain.History;
import CookSave.CookSaveback.History.dto.*;
import CookSave.CookSaveback.History.repository.HistoryRepository;
import CookSave.CookSaveback.HistoryIngredient.domain.HistoryIngredient;
import CookSave.CookSaveback.HistoryIngredient.repository.HistoryIngredientRepository;
import CookSave.CookSaveback.Member.domain.Member;
import CookSave.CookSaveback.Member.repository.MemberRepository;
import CookSave.CookSaveback.Recipe.domain.Recipe;
import CookSave.CookSaveback.Recipe.repository.RecipeRepository;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class HistoryService {
    private final MemberRepository memberRepository;
    private final RecipeRepository recipeRepository;
    private final HistoryRepository historyRepository;
    private final HistoryIngredientRepository historyIngredientRepository;

    public void updateBudget(Member member, BudgetRequestDto budgetRequestDto){
        member.updateBudget(budgetRequestDto.getBudget());
        memberRepository.save(member);
    }

    public void createRecipeHistory(Member member, Long recipeId, RecipeHistoryReqDto recipeHistoryReqDto) {
        // History 저장
        Recipe recipe = recipeRepository.findById(recipeId)
                .orElseThrow(() -> new EntityNotFoundException("recipeId " + recipeId + "인 레시피가 존재하지 않습니다."));
        History history = new History(member, recipe.getName(), recipeHistoryReqDto.getTotal());
        historyRepository.save(history);

        // HistoryIngredient들 저장
        List<HistoryIngredientReqDto> historyIngredientReqDtos = recipeHistoryReqDto.getIngredients();
        for(HistoryIngredientReqDto ingredientReqDto : historyIngredientReqDtos){
            HistoryIngredient historyIngredient = new HistoryIngredient(history, ingredientReqDto.getName(), ingredientReqDto.getAmount(), ingredientReqDto.getPrice());
            historyIngredientRepository.save(historyIngredient);
        }
    }

    public void createInputHistory(Member member, InputHistoryReqDto inputHistoryReqDto) {
        // History 저장
        History history = new History(member, inputHistoryReqDto.getName(), inputHistoryReqDto.getTotal());
        historyRepository.save(history);

        // HistoryIngredient들 저장
        List<HistoryIngredientReqDto> historyIngredientReqDtos = inputHistoryReqDto.getIngredients();
        for(HistoryIngredientReqDto ingredientReqDto : historyIngredientReqDtos){
            HistoryIngredient historyIngredient = new HistoryIngredient(history, ingredientReqDto.getName(), ingredientReqDto.getAmount(), ingredientReqDto.getPrice());
            historyIngredientRepository.save(historyIngredient);
        }
    }

    public HistoryDetailResDto getHistoryDetail(Long historyId, Member member){
        History history = historyRepository.findById(historyId)
                .orElseThrow(() -> new EntityNotFoundException("hisotoryId가 " + historyId + "인 요리 내역이 존재하지 않습니다."));
        List<HistoryIngredient> historyIngredients = historyIngredientRepository.findAllByHistory(history);
        List<HistoryIngredientResDto> ingredients = new ArrayList<>();

        for(HistoryIngredient historyIngredient : historyIngredients){
            HistoryIngredientResDto ingredient = new HistoryIngredientResDto(historyIngredient);
            ingredients.add(ingredient);
        }
        return new HistoryDetailResDto(history, ingredients);
    }

    public void deleteHistory(Member member, Long historyId){
        History history = historyRepository.findByHistoryIdAndMember(historyId, member)
                .orElseThrow(() -> new IllegalArgumentException("잘못된 접근입니다."));
        List<HistoryIngredient> historyIngredients = historyIngredientRepository.findAllByHistory(history);
        historyIngredientRepository.deleteAll(historyIngredients);
        historyRepository.delete(history);
    }
}
