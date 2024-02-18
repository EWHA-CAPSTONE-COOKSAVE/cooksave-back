package CookSave.CookSaveback.Ingredient.service;

import CookSave.CookSaveback.Icon.domain.Icon;
import CookSave.CookSaveback.Icon.repository.IconRepository;
import CookSave.CookSaveback.Ingredient.domain.Ingredient;
import CookSave.CookSaveback.Ingredient.dto.IngredientRequestDto;
import CookSave.CookSaveback.Ingredient.dto.IngredientResponseDto;
import CookSave.CookSaveback.Ingredient.repository.IngredientRepository;
import CookSave.CookSaveback.Member.domain.Member;
import CookSave.CookSaveback.Member.service.MemberService;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class IngredientService {
    private final MemberService memberService;
    private final IconRepository iconRepository;
    private final IngredientRepository ingredientRepository;

    // 전체 재료 조회
    public List<IngredientResponseDto> getIngredientList() {
        List<Ingredient> ingredients = new ArrayList<>();
        Member member = memberService.getLoginMember();
        ingredients = ingredientRepository.findAllByMember(member);

        List<IngredientResponseDto> ingredientList = new ArrayList<>();
        for (Ingredient ingredient:ingredients){
            IngredientResponseDto ingredientResponseDto = new IngredientResponseDto(ingredient);
            ingredientList.add(ingredientResponseDto);
        }
        return ingredientList;
    }

    public void createIngredients(List<IngredientRequestDto> ingredientDtos){
        List<Ingredient> ingredients = new ArrayList<>();

        // 현재 로그인한 member 불러오기
        Member member = memberService.getLoginMember();

        for (IngredientRequestDto ingredientDto : ingredientDtos){
            Icon icon = iconRepository.findById(ingredientDto.getIconId())
                    .orElseThrow(() -> new EntityNotFoundException("iconId " + ingredientDto.getIconId() + "인 아이콘이 존재하지 않습니다."));
            Ingredient ingredient = Ingredient.builder()
                    .member(member)
                    .tag(null)  // 태그 지정 방법 결정 후 수정 필요
                    .icon(icon)
                    .name(ingredientDto.getName())
                    .price(ingredientDto.getPrice())
                    .amount(ingredientDto.getAmount())
                    .build();
            ingredientRepository.save(ingredient);
        }
    }
}
