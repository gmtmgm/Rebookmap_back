package BookMap.PentaRim.Login.Controller;


import lombok.RequiredArgsConstructor;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.session.jdbc.JdbcIndexedSessionRepository;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class ExpriedDeleteOneOfDay {


    private final JdbcIndexedSessionRepository sessionRepository;
    @Scheduled(cron = "0 0 18 * * *")
    public void DeleteExpired() {
        sessionRepository.cleanUpExpiredSessions();
    }
}
