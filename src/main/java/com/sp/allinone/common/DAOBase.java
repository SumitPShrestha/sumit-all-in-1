package com.sp.allinone.common;

import com.sp.allinone.config.persistance.ClientConfigParameter;
import com.sp.allinone.config.persistance.PlatformConfiguration;
import com.sp.allinone.persistance.model.UserSecurityMetadata;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;

import java.util.List;

/**
 * Created by i82298 on 12/27/2016.
 */
public interface DAOBase<T extends Model> {



    public Class getModelClass();

}
