package com.kerry.system.model;

import com.kerry.system.model.base.Region;
import org.beetl.sql.core.annotatoin.Table;

import java.io.Serializable;

/**
 * Created by wangshen on 2017/4/10.
 */
@Table(name = "c_region")
public class RegionModel extends Region implements Serializable {
}
