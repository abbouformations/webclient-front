package ma.formations.spring.rest.domaine;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.Date;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class EmpVo implements Serializable {
	private Long id;
	private String name;
	private Double salaire;
	private String fonction;
}