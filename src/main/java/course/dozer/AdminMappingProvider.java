package course.dozer;

import static org.dozer.loader.api.FieldsMappingOptions.customConverter;
import static org.dozer.loader.api.TypeMappingOptions.mapNull;
import static org.dozer.loader.api.TypeMappingOptions.oneWay;
import static org.dozer.loader.api.TypeMappingOptions.wildcard;

import java.util.Arrays;
import java.util.Collection;

import org.dozer.loader.api.BeanMappingBuilder;
import org.dozer.loader.api.TypeMappingOptions;
import org.springframework.stereotype.Component;

import course.data.model.Admin;
import course.dto.AdminDTO;

@Component
public class AdminMappingProvider implements MappingProvider {

	@Override
	public Collection<BeanMappingBuilder> getMapperConfigurations() {
		return Arrays.asList(adminModelToAdminDTOMapping());
	}

    private BeanMappingBuilder adminModelToAdminDTOMapping() {
        return new BeanMappingBuilder() {
            @Override
            protected void configure() {
                mapping(Admin.class, AdminDTO.class, oneWay(), mapNull(false))
                .fields("createTime", "createTime", customConverter(DateStringConvertor.class))
                .fields("disable_by.name", "disable_by")
                .fields("disableTime", "disableTime", customConverter(DateStringConvertor.class));
            }
        };
    }
    
    private BeanMappingBuilder adminDTOToAdminModelMapping(){
    	return new BeanMappingBuilder(){
			@Override
			protected void configure() {
				mapping(AdminDTO.class, Admin.class, oneWay(), mapNull(false), wildcard(false))
				.fields("name", "name")
				.fields("password", "password")
				.fields("enable", "enable");
			}
    	};
    }
}
