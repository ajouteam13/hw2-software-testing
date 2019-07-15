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
import static org.mockito.Mockito.*;

@RunWith(MockitoJUnitRunner.class)
public class IdolServiceTest {
    @Mock
    private IdolRepository idolRepository;

    @InjectMocks
    private IdolService idolService;

    // 김지원
    @Test 
    public void 그룹명으로멤버찾기테스트() {
        when(idolService.findMemberByGroupName("BTS")).thenReturn(new Idol("BTS", "뷔", 25, "비주얼"));
        String groupName = idolService.findMemberByGroupName("BTS").getGroupName();
        assertThat(groupName, is("BTS"));
    }
    // 김지원
    @Test 
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
    //임희균
    @Test(expected = IllegalArgumentException.class)
    public void 아이돌이아닐때익셉션발생테스트() { //그룹 이름으로 길구봉구를 저장하면 Exception 발생하는지 테스트
        Idol idol = mock(Idol.class);
        doThrow(new IllegalArgumentException()).when(idol).setGroupName("길구봉구");
        idol.setGroupName("길구봉구");
    }
    //임희균
    @Test 
    public void shouldReturnProperAge() {  //그룹명으로 검색하면 객체를 리턴받고 정확한 나이를 리턴하는지 테스트
        when(idolService.findMemberByGroupName("BTS")).thenReturn(new Idol("BTS", "진", 28, "보컬"));
        assertThat(idolService.findMemberByGroupName("BTS").getAge(), is(28));
    }

    //임희균
    @Test 
    public void 그룹명으로멤버찾고Service메소드호출검증테스트() {  //그룹명으로 검색하면 idolRepository의 findMemberByGroupName 메소드가 실행되는지 테스트
        when(idolService.findMemberByGroupName(anyString())).thenReturn(new Idol("BTS", "진", 28, "보컬"));
        String memberName = idolService.findMemberByGroupName(anyString()).getMemberName();
        verify(idolRepository, times(1)).findMemberByGroupName(anyString());
    }
}
