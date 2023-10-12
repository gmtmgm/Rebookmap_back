package BookMap.PentaRim.service;


import lombok.RequiredArgsConstructor;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.session.jdbc.JdbcIndexedSessionRepository;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class ExpriedDeleteOneOfDay {

    //중복돼서 쌓이는 세션 막기 위해 디비에서 세션 주기적으로 삭제하는 코드 추가

    private final JdbcIndexedSessionRepository sessionRepository;
    @Scheduled(cron = "0 0 18 * * *")
    public void DeleteExpired() {
        sessionRepository.cleanUpExpiredSessions();
    }
}
