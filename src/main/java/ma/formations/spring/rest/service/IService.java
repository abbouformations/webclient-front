package ma.formations.spring.rest.service;

import ma.formations.spring.rest.domaine.EmpVo;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface IService {
    Flux<EmpVo> getAllEmployees();
    Mono<EmpVo> getEmployeeById(Long ID);

    Mono<EmpVo> createEmployee(EmpVo vo);

    Mono<EmpVo> updateEmployee(Long id,EmpVo vo);
    Mono<Void> deleteEmployee(Long id);
}
