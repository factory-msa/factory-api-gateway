## Factory API Gateway Service

### Gateway
- 클라이언트와 서비스 사이에 위치한 Proxy 역할의 API Gateway
- 클라이언트는 각 서비스의 엔드포인트 대신 API Gateway 로 Call -> Gateway 는 설정값에 따라 각 서비스를 호출하고, 응답을 클라이언트에 전달하는 역할
- Spring Cloud Gateway 의 구성은 크게 `Route`, `Predicate`, `Filter` 로 구성

### Factory Gateway 서비스는 다음과 같은 역할을 담당
- `GlobalTransactionId`
  - 각 서비스의 추적, 로깅 등에 사용될 `글로벌 트랜잭션 ID` 를 생성하고, `HTTP Header` 에 설정
- `Request Logging`
  - 모든 클라이언트의 요청 데이터를 DB 에 저장
- TODO: `Authentication & Authorization`
- TODO: `Circuit Breaker`

### Gateway Glossary
![image](https://github.com/JuHyun419/study/assets/50076031/5b21f55f-5789-4ae4-8741-f9f930a86c21)

#### Route
- 서비스의 고유 id, 요청 url, Predicates, Filter 로 구성
- 요청 uri 의 조건이 predicates 와 일치하는지 확인 후 요청 경로 매칭
#### Predicate
- API Gateway 로 들어온 클라이언트의 요청이 조건을 만족하는지 검증
#### Filter
- API Gateway 로 들어온 클라이언트의 요청에 Filter 를 적용하여 선처리 및 후처리를 적용

### docs
https://docs.spring.io/spring-cloud-gateway/docs/current/reference/html/
