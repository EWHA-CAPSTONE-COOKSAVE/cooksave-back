package CookSave.CookSaveback.History.service;

import CookSave.CookSaveback.History.domain.History;
import CookSave.CookSaveback.History.dto.BudgetRequestDto;
import CookSave.CookSaveback.History.dto.HistoryIngredientReqDto;
import CookSave.CookSaveback.History.dto.RecipeHistoryReqDto;
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
}
