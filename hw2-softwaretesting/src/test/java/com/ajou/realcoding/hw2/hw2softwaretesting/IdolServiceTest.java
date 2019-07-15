package com.ajou.realcoding.hw2.hw2softwaretesting;

import com.ajou.realcoding.hw2.hw2softwaretesting.domain.Idol;
import com.ajou.realcoding.hw2.hw2softwaretesting.repository.IdolRepository;
import com.ajou.realcoding.hw2.hw2softwaretesting.service.IdolService;
import org.hamcrest.Matcher;
import org.hamcrest.MatcherAssert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.List;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.Is.is;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class IdolServiceTest {
    @Mock
    private IdolRepository idolRepository;

    @InjectMocks
    private IdolService idolService;


    @Test // 김지원
    public void 그룹명으로멤버찾기테스트() {
        when(idolService.findMemberByGroupName("BTS")).thenReturn(new Idol("BTS", "뷔", 25, "비주얼"));
        String groupName = idolService.findMemberByGroupName("BTS").getGroupName();
        assertThat(groupName, is("BTS"));
    }

    @Test // 김지원
    public void 그룹명으로모든멤버찾기테스트(){
        List<Idol> idols = Mockito.mock(List.class);
        Mockito.when(idolService.findAllMembersByGroupName("BTS")).thenReturn(idols);
        Mockito.when(idols.size()).thenReturn(7);
        MatcherAssert.assertThat(idols.size(), is(7));
    }

    //박수연
    @Test //가짜 객체 만들어서 그룹이름을 얻어올때 bts가 반환되는지 확인.
    public void testIdolMockWhenThen()
    {
        Idol idol = mock(Idol.class);
        when(idol.getGroupName()).thenReturn("BTS");
        assertTrue("BTS".equals(idol.getGroupName()));
    }

    //박수연
    @Test //find함수로 아이돌 검색하면 가짜 아이돌객체 리턴하고 해당객체의 멤버이름이 맞는지 확인.
    public void testIfIdolisInGroup()
    {
        when(idolService.findByName("BTS")).thenReturn(new Idol("BTS","뷔",25,"dancer"));
        String idolname = idolRepository.findByName("BTS").getMemberName();
        assertThat(idolname,is("뷔"));
    }
    //박수연
    @Test //가짜 아이돌 객체 생성하고 업데이트함수로 업데이트한다음 내용 잘 들어 갔는지 확인.
    public void updateInfoTest()
    {
        Idol idol = mock(Idol.class);
        Idol idolinfo = idolService.UpdateIdolProfile("BTS","진",25,"dancer");
        assertThat(idolinfo.getMemberName(),is("진"));

    }


}
