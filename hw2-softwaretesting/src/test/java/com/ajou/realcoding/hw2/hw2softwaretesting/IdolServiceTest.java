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
}
