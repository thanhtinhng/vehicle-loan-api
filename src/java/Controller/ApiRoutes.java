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

    //Auth
    String LOGIN = "/api/auth/login";
    String REGISTER = "/api/auth/register";
    
    //Guest
    String XE_DS = "/api/xe/ds_xe";
    String DS_XE_CON_HANG = "/api/xe/ds_xeConHang";
    String SORT_PRICE = "/api/xe/sapXepGiaTang";
    String FILTER_DS_XE = "/api/xe/filterDsXe";
    
    //User
    String THONG_TIN_USER = "/api/user/thongTinUser";
    String TAO_HOP_DONG = "/api/user/taoHopDong";
    String NAP_TIEN = "/api/user/napTien";
    String THONG_TIN_HOP_DONG = "/api/user/thongTinHopDong";
    
    //Admin
    String USER_DS = "/api/admin/ds_user";
    String THONG_TIN_CUA_HANG = "/api/admin/thongTinCuaHang";
    String DS_HOP_DONG_CHO_DUYET = "/api/admin/hopDongChoDuyet";
    String DUYET_HOP_DONG = "/api/admin/duyetHopDong";
    String TU_CHOI_HOP_DONG = "/api/admin/tuChoiHopDong";
    String DS_HOP_DONG = "/api/admin/thongTinHopDong";
}
