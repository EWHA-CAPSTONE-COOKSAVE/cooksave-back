package CookSave.CookSaveback.History.service;

import CookSave.CookSaveback.History.dto.BudgetRequestDto;
import CookSave.CookSaveback.Member.domain.Member;
import CookSave.CookSaveback.Member.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class HistoryService {
    private final MemberRepository memberRepository;

    public void updateBudget(Member member, BudgetRequestDto budgetRequestDto){
        member.updateBudget(budgetRequestDto.getBudget());
        memberRepository.save(member);
    }
}
