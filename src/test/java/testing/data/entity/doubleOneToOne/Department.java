package testing.data.entity.doubleOneToOne;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@Table(name = "TBL_Department")
public class Department {// Department表有一个MGR_ID外键列

	@JoinColumn(name = "MGR_ID", unique = true)
	@OneToOne(fetch = FetchType.LAZY)
	private Manager mgr;
}