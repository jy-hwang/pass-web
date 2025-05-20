package com.fastcampus.pass.service.statistics;

import com.fastcampus.pass.repository.statistics.StatisticsRepository;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class StatisticsServiceTest {

  // 서비스 로직에서 mock 으로 대체해야하는 부분을 찾기.
  @Mock
  private StatisticsRepository statisticsRepository;

  @InjectMocks
  private StatisticsService statisticsService;

  @Nested
  @DisplayName("통계 데이터를 기반으로 차트 만들기")
  class MakeChartData {
    final LocalDateTime to = LocalDateTime.of(2025, 5, 19, 0, 0);

    @DisplayName("통계 데이터가 있을 때")
    @Test
    void makeChartData_when_hasStatistics() {
      // given
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

    @DisplayName("통계 데이터가 없을 때")
    @Test
    void makeChartData_when_notHasStatistics() {
      // when
      when(statisticsRepository.findByStatisticsAtBetweenAndGroupBy(eq(to.minusDays(10)), eq(to))).thenReturn(Collections.emptyList());
      final ChartData chartData = statisticsService.makeChartData(to);

      // then
      verify(statisticsRepository, times(1)).findByStatisticsAtBetweenAndGroupBy(eq(to.minusDays(10)), eq(to));

      assertNotNull(chartData);
      assertTrue(chartData.getLabels().isEmpty());

      assertTrue(chartData.getAttendedCounts().isEmpty());
      assertTrue(chartData.getCancelledCounts().isEmpty());
    }
  }

}
