package com.example.workItem;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.example.user.User;
import com.example.work.entity.Work;

import java.util.List;
import java.util.Optional;

@Repository
public interface WorkItemRepository extends JpaRepository<WorkItem,Long> {
    List<WorkItem> findByWorkId(Long workId);
    @Query("SELECT wi FROM WorkItem wi WHERE wi.work.id = :workId AND wi.user.id = :otherId")
    List<WorkItem> findByWorkIdAndOtherUserId(@Param("workId") Long workId, @Param("otherId") Long otherId);

    List<WorkItem> findByWorkIdAndUserId(Long workId, Long userId);
    List<WorkItem> findByUserIdAndWorkId(Long userId, Long workId);


    @Query("SELECT DISTINCT wi.user.id FROM WorkItem wi WHERE wi.work.id = :workId")
    List<Long> findDistinctUserIdsByWorkId(@Param("workId") Long workId); // 특정 Work에 대한 모든 User ID를 반환하는 쿼리

}
