package test;

import java.math.BigDecimal;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.wooxun.geekdol.geekstore.model.CooperationApplication;
import com.wooxun.geekdol.geekstore.service.CooperationApplicationService;

/**
 * @Title
 * @Description
 * @version 1.0.0
 * @Author 万通	
 * @CreateDate 2016年7月15日 
 * @param 
 * @see 
 * @modified 修改履历
 *==========================================================
 * No    修改人员			修改日期  	   			描述
 * 1. 	 万通	2016年7月15日  下午4:53:17 		创建
 *==========================================================
 * 
 */
public class CooperationApplicationTest {

	public static void main(String[] args) {
		String file[] = {"spring.xml","spring-mybatis.xml"};
		ApplicationContext context = new ClassPathXmlApplicationContext(file);
		CooperationApplicationService service = (CooperationApplicationService) context.getBean("cooperationApplicationServiceImpl");
		CooperationApplication bean = new CooperationApplication();
		bean.setVillageId((long) 1);
		bean.setApplicationName("admin1");
		bean.setContactNumber("15138976767");
		bean.setStoreName("storeName");
		//bean.setTypeId((long) 1);
		bean.setStoreAdress("升龙又一城");
		bean.setSize(new BigDecimal(12.56));
		bean.setDelFlag("0");
		service.insertCooperationApplication(bean);
	}

}
