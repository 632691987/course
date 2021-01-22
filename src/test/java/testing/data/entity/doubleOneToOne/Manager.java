package testing.data.entity.doubleOneToOne;

import javax.persistence.Entity;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@Table(name="TBL_Manager")
public class Manager {
	
	@OneToOne(mappedBy="mgr")
	private Department dept;
}