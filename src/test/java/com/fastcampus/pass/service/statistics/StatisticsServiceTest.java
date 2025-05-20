package com.fastcampus.pass.service.statistics;

import com.fastcampus.pass.repository.statistics.StatisticsRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class StatisticsServiceTest {

  // 서비스 로직에서 mock 으로 대체해야하는 부분을 찾기.
  @Mock
  private StatisticsRepository statisticsRepository;

  @InjectMocks
  private StatisticsService statisticsService;

  @Test
  public void test_makeChartData() {
    // given
    final LocalDateTime to = LocalDateTime.of(2025, 5, 19, 0, 0);

    List<AggregatedStatistics> aggregatedStatisticsList = List.of(
        new AggregatedStatistics(to.minusDays(1), 15, 10, 5),
        new AggregatedStatistics(to, 10, 8, 2)
    );

    // when
    when(statisticsRepository.findByStatisticsAtBetweenAndGroupBy(eq(to.minusDays(10)), eq(to))).thenReturn(aggregatedStatisticsList);
    final ChartData chartData = statisticsService.makeChartData(to);

    // then
    verify(statisticsRepository, times(1)).findByStatisticsAtBetweenAndGroupBy(eq(to.minusDays(10)), eq(to));

    assertNotNull(chartData);
    assertEquals(new ArrayList<>(List.of("05-18", "05-19")), chartData.getLabels());
    assertEquals(new ArrayList<>(List.of(10L, 8L)), chartData.getAttendedCounts());
    assertEquals(new ArrayList<>(List.of(5L, 2L)), chartData.getCancelledCounts());

  }

}
