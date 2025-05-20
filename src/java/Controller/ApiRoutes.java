/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package Controller;

/**
 *
 * @author Windows 10
 */
public interface ApiRoutes {

    String LOGIN = "/api/auth/login";
    String REGISTER = "/api/auth/register";
    String USER_DS = "/api/admin/ds_user";
    String XE_DS = "/api/xe/ds_xe";
    String THONG_TIN_USER = "/api/user/thongTinUser";
    String DS_XE_CON_HANG = "/api/xe/ds_xeConHang";
    String FILTER_DS_XE = "/api/xe/filterDsXe";
    String THONG_TIN_CUA_HANG = "/api/admin/thongTinCuaHang";
    String SORT_PRICE = "/api/xe/sapXepGiaTang";
}
