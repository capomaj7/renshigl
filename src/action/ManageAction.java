package action;


import java.io.File;
import java.io.IOException;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import model.Baoxianfuli;
import model.Bumen;
import model.Gongzi;
import model.Hetong;
import model.Kaoqin;
import model.Menmian;
import model.Peixun;
import model.User;
import model.Yuangong;
import model.Zhiwei;
import model.Zhiweijilu;

import org.apache.struts2.ServletActionContext;

import util.Pager;
import util.Util;

import com.opensymphony.xwork2.ActionSupport;

import dao.BaoxianfuliDao;
import dao.BumenDao;
import dao.GongziDao;
import dao.HetongDao;
import dao.KaoqinDao;
import dao.MenmianDao;
import dao.PeixunDao;
import dao.UserDao;
import dao.YuangongDao;
import dao.ZhiweiDao;
import dao.ZhiweijiluDao;

public class ManageAction extends ActionSupport{
	
	
	private static final long serialVersionUID = -4304509122548259589L;
	
	private UserDao userDao;
	
	private String url = "./";
	private MenmianDao menmianDao;
	private String title;
	private String prebumenid;
	public UserDao getUserDao() {
		return userDao;
	}

	public void setUserDao(UserDao userDao) {
		this.userDao = userDao;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}
	
	//程序入口界面
	public String index(){
		HttpServletRequest request = ServletActionContext.getRequest();
		Util.init(request);
		HttpSession session = request.getSession();
		User user = (User)session.getAttribute("user");
		if(user==null){
			return "success2";
		}else{
			return "success1";
		}
	}
	
	
	//用户登录操作
	public String login() throws IOException {
		HttpServletRequest request = ServletActionContext.getRequest();
		String username = request.getParameter("username");
		String password = request.getParameter("password");
		String role = request.getParameter("role");
		User user = userDao.selectBean(" where username = '"+username +"' and password= '"
				+password +"' and userlock=0 and role="+role);
		if (user!=null) {
			HttpSession session = request.getSession();
			session.setAttribute("user", user);
			this.setUrl("index");
			return "redirect";
		} else {
			HttpServletResponse response = ServletActionContext.getResponse();
			response.setCharacterEncoding("gbk");response.setContentType("text/html; charset=gbk");
			response
					.getWriter()
					.print(
							"<script language=javascript>alert('用户名或者密码错误');window.location.href='login.jsp';</script>");
		}
		return null;
	}
	
	//用户退出操作
	public String loginout() {
		HttpServletRequest request = ServletActionContext.getRequest();
		HttpSession session = request.getSession();
		session.removeAttribute("user");
		this.setUrl("login.jsp");
		return SUCCESS;
	}
	
	
	//跳转到修改密码页面
	public String changepwd() {
		this.setUrl("user/password.jsp");
		return SUCCESS;
	}
	
	//修改密码操作
	public void changepwd2() throws IOException {
		HttpServletRequest request = ServletActionContext.getRequest();
		
		HttpSession session = request.getSession();
		User u = (User)session.getAttribute("user");
		String password1 = request.getParameter("password1");
		String password2 = request.getParameter("password2");
		User bean = userDao.selectBean(" where username= '"+u.getUsername()+"' and password= '"+password1+"'");
		if(bean!=null){
			bean.setPassword(password2);
			userDao.updateBean(bean);
			HttpServletResponse response = ServletActionContext.getResponse();
			response.setCharacterEncoding("gbk");response.setContentType("text/html; charset=gbk");
			response
					.getWriter()
					.print(
							"<script language=javascript>alert('修改成功');</script>");
		}else{
			HttpServletResponse response = ServletActionContext.getResponse();
			response.setCharacterEncoding("gbk");response.setContentType("text/html; charset=gbk");
			response
					.getWriter()
					.print(
							"<script language=javascript>alert('用户名或者密码错误');</script>");
		}
	}
	
	
	private BumenDao bumenDao;
	
	
	public BumenDao getBumenDao() {
		return bumenDao;
	}

	public void setBumenDao(BumenDao bumenDao) {
		this.bumenDao = bumenDao;
	}

	//部门列表
	public String bumenlist()  {
		HttpServletRequest request = ServletActionContext.getRequest();
		String name = request.getParameter("name");
		
		StringBuffer sb = new StringBuffer();
		sb.append(" where ");
		StringBuffer sb2 = new StringBuffer();
		sb2.append(" where ");

		if(name!=null&&!"".equals(name)){
			sb.append("name like '%"+name+"%'");
			sb.append(" and ");
			sb2.append("name like '%"+name+"%'");
			sb2.append(" and ");

			request.setAttribute("name", name);
		}
		sb.append(" bumenlock=0 order by id desc");
		String where = sb.toString();
		sb2.append(" bumenlock=0 ");
		String where2 = sb2.toString();
		
		int currentpage = 1;
		int pagesize =10;
		if(request.getParameter("pagenum")!=null){
			currentpage = Integer.parseInt(request.getParameter("pagenum"));
		}
		int total = bumenDao.selectBeanCount(where2);
		request.setAttribute("list", bumenDao.selectBeanList((currentpage - 1) * pagesize, pagesize, where));
		request.setAttribute("pagerinfo", Pager.getPagerNormal(total, pagesize,
				currentpage, "method!bumenlist", "共有" + total + "条记录"));
		this.setUrl("bumen/bumenlist.jsp");
		return SUCCESS;
	}

//跳转到添加部门页面
	public String bumenadd() {
		this.setUrl("bumen/bumenadd.jsp");
		return SUCCESS;
	}
//添加部门操作
	public void bumenadd2() throws IOException {
		HttpServletRequest request = ServletActionContext.getRequest();
		String name = request.getParameter("name");
		Bumen bean = new Bumen();
		bean.setName(name);
		bumenDao.insertBean(bean);
		HttpServletResponse response = ServletActionContext.getResponse();
		response.setCharacterEncoding("gbk");response.setContentType("text/html; charset=gbk");
		response
				.getWriter()
				.print(
						"<script language=javascript>alert('操作成功');window.location.href='method!bumenlist';</script>");

		
	}
//跳转到更新部门页面
	public String bumenupdate() {
		HttpServletRequest request = ServletActionContext.getRequest();
		Bumen bean = bumenDao.selectBean(" where id= "+request.getParameter("id"));
		request.setAttribute("bean", bean);
		this.setUrl("bumen/bumenupdate.jsp");
		return SUCCESS;
	}
//更新部门操作
	public void bumenupdate2() throws IOException {
		HttpServletRequest request = ServletActionContext.getRequest();
		String name = request.getParameter("name");	
		String bianhao = request.getParameter("bianhao");	
		Bumen bean = bumenDao.selectBean(" where id= "+request.getParameter("id"));
		bean.setName(name);
		bean.setBianhao(Integer.parseInt(bianhao));
		bumenDao.updateBean(bean);
		HttpServletResponse response = ServletActionContext.getResponse();
		response.setCharacterEncoding("gbk");response.setContentType("text/html; charset=gbk");
		response
				.getWriter()
				.print(
						"<script language=javascript>alert('操作成功');window.location.href='method!bumenlist';</script>");
	}

//查看部门操作
	public String bumenupdate3() {
		HttpServletRequest request = ServletActionContext.getRequest();
		Bumen bean = bumenDao.selectBean(" where id= "+request.getParameter("id"));
		request.setAttribute("bean", bean);
		this.setUrl("bumen/bumenupdate3.jsp");
		return SUCCESS;
	}
//删除部门操作
	public void bumendelete() throws IOException {
		HttpServletRequest request = ServletActionContext.getRequest();
		Bumen bean = bumenDao.selectBean(" where id= "+request.getParameter("id"));
		bean.setBumenlock(1);
		bumenDao.updateBean(bean);
		HttpServletResponse response = ServletActionContext.getResponse();
		response.setCharacterEncoding("gbk");response.setContentType("text/html; charset=gbk");
		response
				.getWriter()
				.print(
						"<script language=javascript>alert('操作成功');window.location.href='method!bumenlist';</script>");
	}
	
	private YuangongDao yuangongDao;


	public YuangongDao getYuangongDao() {
		return yuangongDao;
	}

	public void setYuangongDao(YuangongDao yuangongDao) {
		this.yuangongDao = yuangongDao;
	}
	
	
	//员工信息列表
	public String yuangonglist()  {
		HttpServletRequest request = ServletActionContext.getRequest();
		request.setAttribute("list2", bumenDao.selectBeanList(0,999," where bumenlock=0 "));
		
		String truename = request.getParameter("truename");
		String yuangonghao = request.getParameter("yuangonghao");
		String bumen = request.getParameter("bumen");
		
		StringBuffer sb = new StringBuffer();
		sb.append(" where ");
		StringBuffer sb2 = new StringBuffer();
		sb2.append(" where ");

		if(truename!=null&&!"".equals(truename)){
			sb.append("truename like '%"+truename+"%'");
			sb.append(" and ");
			sb2.append("truename like '%"+truename+"%'");
			sb2.append(" and ");

			request.setAttribute("truename", truename);
		}
		
		if(yuangonghao!=null&&!"".equals(yuangonghao)){
			sb.append("yuangonghao like '%"+yuangonghao+"%'");
			sb.append(" and ");
			sb2.append("yuangonghao like '%"+yuangonghao+"%'");
			sb2.append(" and ");

			request.setAttribute("yuangonghao", yuangonghao);
		}
		
		if(bumen!=null&&!"".equals(bumen)){
			sb.append("bumen.name like '%"+bumen+"%'");
			sb.append(" and ");
			sb2.append("bumen.name like '%"+bumen+"%'");
			sb2.append(" and ");

			request.setAttribute("bumen", bumen);
		}
		sb.append(" yuangonglock=0 order by id desc");
		String where = sb.toString();
		sb2.append(" yuangonglock=0 ");
		String where2 = sb2.toString();
		
		int currentpage = 1;
		int pagesize =10;
		if(request.getParameter("pagenum")!=null){
			currentpage = Integer.parseInt(request.getParameter("pagenum"));
		}
		int total = yuangongDao.selectBeanCount(where2);
		request.setAttribute("list", yuangongDao.selectBeanList((currentpage - 1) * pagesize, pagesize, where));
		request.setAttribute("pagerinfo", Pager.getPagerNormal(total, pagesize,
				currentpage, "method!yuangonglist", "共有" + total + "条记录"));
		this.setUrl("yuangong/yuangonglist.jsp");
		return SUCCESS;
	}

//跳转到添加员工信息页面
	public String yuangongadd() {
		HttpServletRequest request = ServletActionContext.getRequest();
		request.setAttribute("list2", bumenDao.selectBeanList(0,999," where bumenlock=0 "));
		request.setAttribute("list3", menmianDao.getAll());
		this.setUrl("yuangong/yuangongadd.jsp");
		return SUCCESS;
	}
//添加员工信息操作
	public void yuangongadd2() throws IOException {
		HttpServletRequest request = ServletActionContext.getRequest();
		String menmianid = request.getParameter("menmian");
		String bumen = request.getParameter("bumen");
		String dizhi = request.getParameter("dizhi");
		String jiguan = request.getParameter("jiguan");
		String lianxidianhua = request.getParameter("lianxidianhua");
		String ruzhishijian = request.getParameter("ruzhishijian");
		String truename = request.getParameter("truename");
		String xingbie = request.getParameter("xingbie");
		String beizhu = request.getParameter("beizhu");
		String xueli = request.getParameter("xueli");
		String yuangonghao = Util.getTime2();
		Yuangong bean = new Yuangong();
		bean.setBumen(bumenDao.selectBean(" where id= "+bumen));
		//System.out.println(menmianid);
		Long menmianid2=Long.parseLong(menmianid);
		bean.setMenmian(menmianDao.getById(menmianid2));
		bean.setCreatetime(new Date());
		bean.setDizhi(dizhi);
		bean.setJiguan(jiguan);
		bean.setLianxidianhua(lianxidianhua);
		bean.setRuzhishijian(ruzhishijian);
		bean.setTruename(truename);
		bean.setXueli(xueli);
		bean.setYuangonghao(yuangonghao);
		bean.setXingbie(xingbie);
		bean.setBeizhu(beizhu);
		yuangongDao.insertBean(bean);
		User user = new User();
		user.setCreatetime(new Date());
		user.setPassword("111111");
		user.setRole(0);
		user.setTruename(truename);
		user.setUsername(yuangonghao);
		user.setYuangong(bean);
		userDao.insertBean(user);
		HttpServletResponse response = ServletActionContext.getResponse();
		response.setCharacterEncoding("gbk");response.setContentType("text/html; charset=gbk");
		response
				.getWriter()
				.print(
						"<script language=javascript>alert('操作成功');window.location.href='method!yuangonglist';</script>");

		
	}
//跳转到更新员工信息页面
	public String yuangongupdate() {
		HttpServletRequest request = ServletActionContext.getRequest();
		request.setAttribute("list2", bumenDao.selectBeanList(0,999," where bumenlock=0 "));
		Yuangong bean = yuangongDao.selectBean(" where id= "+request.getParameter("id"));
		request.setAttribute("bean", bean);
		this.setUrl("yuangong/yuangongupdate.jsp");
		return SUCCESS;
	}
//更新员工信息操作
	public void yuangongupdate2() throws IOException {
		HttpServletRequest request = ServletActionContext.getRequest();
		String dizhi = request.getParameter("dizhi");
		String jiguan = request.getParameter("jiguan");
		String lianxidianhua = request.getParameter("lianxidianhua");
		String ruzhishijian = request.getParameter("ruzhishijian");
		String truename = request.getParameter("truename");
		String xueli = request.getParameter("xueli");
		String xingbie = request.getParameter("xingbie");
		String beizhu = request.getParameter("beizhu");
		
		Yuangong bean = yuangongDao.selectBean(" where id= "+request.getParameter("id"));
		bean.setCreatetime(new Date());
		bean.setDizhi(dizhi);
		bean.setJiguan(jiguan);
		bean.setLianxidianhua(lianxidianhua);
		bean.setRuzhishijian(ruzhishijian);
		bean.setTruename(truename);
		bean.setXueli(xueli);
		bean.setXingbie(xingbie);
		bean.setBeizhu(beizhu);
		yuangongDao.updateBean(bean);
		HttpServletResponse response = ServletActionContext.getResponse();
		response.setCharacterEncoding("gbk");response.setContentType("text/html; charset=gbk");
		response
				.getWriter()
				.print(
						"<script language=javascript>alert('操作成功');window.location.href='method!yuangonglist';</script>");
	}

//查看员工信息操作
	public String yuangongupdate3() {
		HttpServletRequest request = ServletActionContext.getRequest();
		Yuangong bean = yuangongDao.selectBean(" where id= "+request.getParameter("id"));
		request.setAttribute("bean", bean);
		this.setUrl("yuangong/yuangongupdate3.jsp");
		return SUCCESS;
	}
//删除员工信息操作
	public void yuangongdelete() throws IOException {
		HttpServletRequest request = ServletActionContext.getRequest();
		Yuangong bean = yuangongDao.selectBean(" where id= "+request.getParameter("id"));
		bean.setYuangonglock(1);
		yuangongDao.updateBean(bean);
		User user = userDao.selectBean(" where yuangong.id= "+bean.getId());
		user.setUserlock(1);
		userDao.updateBean(user);
		HttpServletResponse response = ServletActionContext.getResponse();
		response.setCharacterEncoding("gbk");response.setContentType("text/html; charset=gbk");
		response
				.getWriter()
				.print(
						"<script language=javascript>alert('操作成功');window.location.href='method!yuangonglist';</script>");
	}
	
	
	
	//职务管理
	public String yuangonglist2()  {
		HttpServletRequest request = ServletActionContext.getRequest();
		request.setAttribute("list2", bumenDao.selectBeanList(0,999," where bumenlock=0 "));
		
		String truename = request.getParameter("truename");
		String yuangonghao = request.getParameter("yuangonghao");
		String bumen = request.getParameter("bumen");
		
		StringBuffer sb = new StringBuffer();
		sb.append(" where ");
		StringBuffer sb2 = new StringBuffer();
		sb2.append(" where ");

		if(truename!=null&&!"".equals(truename)){
			sb.append("truename like '%"+truename+"%'");
			sb.append(" and ");
			sb2.append("truename like '%"+truename+"%'");
			sb2.append(" and ");

			request.setAttribute("truename", truename);
		}
		
		if(yuangonghao!=null&&!"".equals(yuangonghao)){
			sb.append("yuangonghao like '%"+yuangonghao+"%'");
			sb.append(" and ");
			sb2.append("yuangonghao like '%"+yuangonghao+"%'");
			sb2.append(" and ");

			request.setAttribute("yuangonghao", yuangonghao);
		}
		
		if(bumen!=null&&!"".equals(bumen)){
			sb.append("bumen.name like '%"+bumen+"%'");
			sb.append(" and ");
			sb2.append("bumen.name like '%"+bumen+"%'");
			sb2.append(" and ");

			request.setAttribute("bumen", bumen);
		}
		sb.append(" yuangonglock=0 order by id desc");
		String where = sb.toString();
		sb2.append(" yuangonglock=0 ");
		String where2 = sb2.toString();
		
		int currentpage = 1;
		int pagesize =10;
		if(request.getParameter("pagenum")!=null){
			currentpage = Integer.parseInt(request.getParameter("pagenum"));
		}
		int total = yuangongDao.selectBeanCount(where2);
		request.setAttribute("list", yuangongDao.selectBeanList((currentpage - 1) * pagesize, pagesize, where));
		request.setAttribute("pagerinfo", Pager.getPagerNormal(total, pagesize,
				currentpage, "method!yuangonglist2", "共有" + total + "条记录"));
		this.setUrl("yuangong/yuangonglist2.jsp");
		return SUCCESS;
	}
	
	
	//提升为部门负责人操作
	public void yuangongdelete3() throws IOException {
		System.out.println("jinlaile");
		HttpServletRequest request = ServletActionContext.getRequest();
		Yuangong bean = yuangongDao.selectBean("where id= "+request.getParameter("id"));
		String titleid=request.getParameter("title");
		List<Yuangong> list = yuangongDao.selectBeanList(0, 9999, " where yuangonglock=0 and bumen.id= "+bean.getBumen().getId());
		/*for(Yuangong y :list){
			y.setZhiwu(0);
			yuangongDao.updateBean(y);
		}*/
		bean.setZhiwu(Integer.parseInt(titleid));
		yuangongDao.updateBean(bean);
		HttpServletResponse response = ServletActionContext.getResponse();
		response.setCharacterEncoding("gbk");response.setContentType("text/html; charset=gbk");
		response
				.getWriter()
				.print(
						"<script language=javascript>alert('操作成功');window.location.href='method!yuangonglist2';</script>");
	}
	
	
	//调动管理
	public String yuangonglist3()  {
		HttpServletRequest request = ServletActionContext.getRequest();
		request.setAttribute("list2", bumenDao.selectBeanList(0,999," where bumenlock=0 "));
		
		String truename = request.getParameter("truename");
		String yuangonghao = request.getParameter("yuangonghao");
		String bumen = request.getParameter("bumen");
		
		StringBuffer sb = new StringBuffer();
		sb.append(" where ");
		StringBuffer sb2 = new StringBuffer();
		sb2.append(" where ");

		if(truename!=null&&!"".equals(truename)){
			sb.append("truename like '%"+truename+"%'");
			sb.append(" and ");
			sb2.append("truename like '%"+truename+"%'");
			sb2.append(" and ");

			request.setAttribute("truename", truename);
		}
		
		if(yuangonghao!=null&&!"".equals(yuangonghao)){
			sb.append("yuangonghao like '%"+yuangonghao+"%'");
			sb.append(" and ");
			sb2.append("yuangonghao like '%"+yuangonghao+"%'");
			sb2.append(" and ");

			request.setAttribute("yuangonghao", yuangonghao);
		}
		
		if(bumen!=null&&!"".equals(bumen)){
			sb.append("bumen.name like '%"+bumen+"%'");
			sb.append(" and ");
			sb2.append("bumen.name like '%"+bumen+"%'");
			sb2.append(" and ");

			request.setAttribute("bumen", bumen);
		}
		sb.append(" yuangonglock=0 order by id desc");
		String where = sb.toString();
		sb2.append(" yuangonglock=0 ");
		String where2 = sb2.toString();
		
		int currentpage = 1;
		int pagesize =10;
		if(request.getParameter("pagenum")!=null){
			currentpage = Integer.parseInt(request.getParameter("pagenum"));
		}
		int total = yuangongDao.selectBeanCount(where2);
		request.setAttribute("list", yuangongDao.selectBeanList((currentpage - 1) * pagesize, pagesize, where));
		request.setAttribute("pagerinfo", Pager.getPagerNormal(total, pagesize,
				currentpage, "method!yuangonglist3", "共有" + total + "条记录"));
		this.setUrl("yuangong/yuangonglist3.jsp");
		return SUCCESS;
	}
	
	
	//跳转到调动员工页面
	public String yuangongupdate5() {
		HttpServletRequest request = ServletActionContext.getRequest();
		request.setAttribute("list2", bumenDao.selectBeanList(0,999," where bumenlock=0 "));
		Yuangong bean = yuangongDao.selectBean(" where id= "+request.getParameter("id"));
		request.setAttribute("bean", bean);
		this.setUrl("yuangong/yuangongupdate5.jsp");
		return SUCCESS;
	}
	//调动员工操作
	public void yuangongupdate6() throws IOException {
		HttpServletRequest request = ServletActionContext.getRequest();
		String bumen = request.getParameter("bumen");
		String prebumenid=request.getParameter("prebumenid");
		
		Yuangong bean = yuangongDao.selectBean(" where id= "+request.getParameter("id"));
		bean.setBumen(bumenDao.selectBean(" where id= "+bumen));
		bean.setPrebumen(bumenDao.selectBean(" where id= "+prebumenid));
		yuangongDao.updateBean(bean);
		HttpServletResponse response = ServletActionContext.getResponse();
		response.setCharacterEncoding("gbk");response.setContentType("text/html; charset=gbk");
		response
				.getWriter()
				.print(
						"<script language=javascript>alert('操作成功');window.location.href='method!yuangonglist3';</script>");
	}
	
	
	private HetongDao hetongDao;


	public HetongDao getHetongDao() {
		return hetongDao;
	}

	public void setHetongDao(HetongDao hetongDao) {
		this.hetongDao = hetongDao;
	}
	
	
	//合同列表
	public String hetonglist()  {
		HttpServletRequest request = ServletActionContext.getRequest();
		
		String truename = request.getParameter("truename");
		
		StringBuffer sb = new StringBuffer();
		sb.append(" where ");
		StringBuffer sb2 = new StringBuffer();
		sb2.append(" where ");

		if(truename!=null&&!"".equals(truename)){
			sb.append("name like '%"+truename+"%'");
			sb.append(" and ");
			sb2.append("name like '%"+truename+"%'");
			sb2.append(" and ");

			request.setAttribute("truename", truename);
		}
		
		
		sb.append(" hetonglock=0 order by id desc");
		String where = sb.toString();
		sb2.append(" hetonglock=0 ");
		String where2 = sb2.toString();
		
		int currentpage = 1;
		int pagesize =10;
		if(request.getParameter("pagenum")!=null){
			currentpage = Integer.parseInt(request.getParameter("pagenum"));
		}
		int total = hetongDao.selectBeanCount(where2);
		request.setAttribute("list", hetongDao.selectBeanList((currentpage - 1) * pagesize, pagesize, where));
		request.setAttribute("pagerinfo", Pager.getPagerNormal(total, pagesize,
				currentpage, "method!hetonglist", "共有" + total + "条记录"));
		this.setUrl("hetong/hetonglist.jsp");
		return SUCCESS;
	}

//跳转到添加合同页面
	public String hetongadd() {
		HttpServletRequest request = ServletActionContext.getRequest();
		request.setAttribute("list2", yuangongDao.selectBeanList(0,999," where yuangonglock=0 "));
		this.setUrl("hetong/hetongadd.jsp");
		return SUCCESS;
	}
	
	
	private File uploadfile;
	
	public File getUploadfile() {
		return uploadfile;
	}

	public void setUploadfile(File uploadfile) {
		this.uploadfile = uploadfile;
	}
	
//添加合同操作
	public void hetongadd2() throws Exception {
		HttpServletRequest request = ServletActionContext.getRequest();
		String beizhu = request.getParameter("beizhu");
		String id = request.getParameter("id");
		String nname = request.getParameter("name");
		String yuangong = request.getParameter("yuangong");
		String filename = request.getParameter("filename");
		Hetong bean = new Hetong();
		bean.setCreatetime(new Date());
		bean.setBeizhu(beizhu);
		System.out.println(id + "+++++++++++++++++++++++");
		bean.setNum(id);
		Yuangong yg = new Yuangong();
		yg.setTruename(nname);
		bean.setName(nname);
		String savaPath = ServletActionContext.getServletContext().getRealPath("/")+ "/uploadfile/";
		String time = new java.text.SimpleDateFormat("yyyyMMddHHmmss").format(new Date()).toString();
		String filename2 = filename.substring(filename.lastIndexOf("\\")+1,filename.length());
		String path = time +"_"+filename2;
		File file = new File(savaPath + path);
		Util.copyFile(uploadfile, file);
		String name = time +".zip";
		Util.createZip(path, name, savaPath);
		bean.setPath(name);
		hetongDao.insertBean(bean);
		HttpServletResponse response = ServletActionContext.getResponse();
		response.setCharacterEncoding("gbk");response.setContentType("text/html; charset=gbk");
		response
				.getWriter()
				.print(
						"<script language=javascript>alert('操作成功');window.location.href='method!hetonglist';</script>");

		
	}



//删除合同操作
	public void hetongdelete() throws IOException {
		HttpServletRequest request = ServletActionContext.getRequest();
		Hetong bean = hetongDao.selectBean(" where id= "+request.getParameter("id"));
		bean.setHetonglock(1);
		hetongDao.updateBean(bean);
		HttpServletResponse response = ServletActionContext.getResponse();
		response.setCharacterEncoding("gbk");response.setContentType("text/html; charset=gbk");
		response
				.getWriter()
				.print(
						"<script language=javascript>alert('操作成功');window.location.href='method!hetonglist';</script>");
	}
	
	private PeixunDao peixunDao;


	public PeixunDao getPeixunDao() {
		return peixunDao;
	}

	public void setPeixunDao(PeixunDao peixunDao) {
		this.peixunDao = peixunDao;
	}
	
	
	
	//培训信息列表
	public String peixunlist()  {
		HttpServletRequest request = ServletActionContext.getRequest();
		
		String truename = request.getParameter("truename");

		
		StringBuffer sb = new StringBuffer();
		sb.append(" where ");
		StringBuffer sb2 = new StringBuffer();
		sb2.append(" where ");

		if(truename!=null&&!"".equals(truename)){
			sb.append(" yuangong.truename like '%"+truename+"%'");
			sb.append(" and ");
			sb2.append(" yuangong.truename like '%"+truename+"%'");
			sb2.append(" and ");

			request.setAttribute("truename", truename);
		}
		
		
		sb.append(" peixunlock=0 order by id desc");
		String where = sb.toString();
		sb2.append(" peixunlock=0 ");
		String where2 = sb2.toString();
		
		int currentpage = 1;
		int pagesize =10;
		if(request.getParameter("pagenum")!=null){
			currentpage = Integer.parseInt(request.getParameter("pagenum"));
		}
		int total = peixunDao.selectBeanCount(where2);
		request.setAttribute("list", peixunDao.selectBeanList((currentpage - 1) * pagesize, pagesize, where));
		request.setAttribute("pagerinfo", Pager.getPagerNormal(total, pagesize,
				currentpage, "method!peixunlist", "共有" + total + "条记录"));
		this.setUrl("peixun/peixunlist.jsp");
		return SUCCESS;
	}
	//培训信息列表
	public String peixunlist4()  {
		HttpServletRequest request = ServletActionContext.getRequest();
		String truename = request.getParameter("truename");
		StringBuffer sb = new StringBuffer();
		sb.append(" where ");
		StringBuffer sb2 = new StringBuffer();
		sb2.append(" where ");
		
		if(truename!=null&&!"".equals(truename)){
			sb.append(" yuangong.truename like '%"+truename+"%'");
			sb.append(" and ");
			sb2.append(" yuangong.truename like '%"+truename+"%'");
			sb2.append(" and ");
			
			request.setAttribute("truename", truename);
		}
		
		
		sb.append(" peixunlock=0 order by id desc");
		String where = sb.toString();
		sb2.append(" peixunlock=0 ");
		String where2 = sb2.toString();
		
		int currentpage = 1;
		int pagesize =10;
		if(request.getParameter("pagenum")!=null){
			currentpage = Integer.parseInt(request.getParameter("pagenum"));
		}
		int total = peixunDao.selectBeanCount(where2);
		request.setAttribute("list", peixunDao.selectBeanList((currentpage - 1) * pagesize, pagesize, where));
		request.setAttribute("pagerinfo", Pager.getPagerNormal(total, pagesize,
				currentpage, "method!peixunlist", "共有" + total + "条记录"));
		this.setUrl("peixun/peixunlist4.jsp");
		return SUCCESS;
	}

//跳转到添加培训信息页面
	public String peixunadd() {
		HttpServletRequest request = ServletActionContext.getRequest();
		request.setAttribute("list2", yuangongDao.selectBeanList(0,999," where yuangonglock=0 "));
		this.setUrl("peixun/peixunadd.jsp");
		return SUCCESS;
	}
//添加培训信息操作
	public void peixunadd2() throws IOException {
		HttpServletRequest request = ServletActionContext.getRequest();
		String peixundidian = request.getParameter("peixundidian");
		String peixunjihua = request.getParameter("peixunjihua");
		String peixunneirong = request.getParameter("peixunneirong");
		String peixunzhouqi = request.getParameter("peixunzhouqi");
		String yuangong = request.getParameter("yuangong");
		Peixun bean = new Peixun();
		bean.setCreatetime(new Date());
		bean.setPeixundidian(peixundidian);
		bean.setPeixunjihua(peixunjihua);
		bean.setPeixunneirong(peixunneirong);
		bean.setPeixunzhouqi(peixunzhouqi);
		bean.setYuangong(yuangongDao.selectBean(" where id= "+yuangong));
	
		peixunDao.insertBean(bean);
	
		HttpServletResponse response = ServletActionContext.getResponse();
		response.setCharacterEncoding("gbk");response.setContentType("text/html; charset=gbk");
		response
				.getWriter()
				.print(
						"<script language=javascript>alert('操作成功');window.location.href='method!peixunlist';</script>");

		
	}
//跳转到更新培训信息页面
	public String peixunupdate() {
		HttpServletRequest request = ServletActionContext.getRequest();
		request.setAttribute("list2", yuangongDao.selectBeanList(0,999," where yuangonglock=0 "));
		Peixun bean = peixunDao.selectBean(" where id= "+request.getParameter("id"));
		request.setAttribute("bean", bean);
		this.setUrl("peixun/peixunupdate.jsp");
		return SUCCESS;
	}
//更新培训信息操作
	public void peixunupdate2() throws IOException {
		HttpServletRequest request = ServletActionContext.getRequest();
		String peixundidian = request.getParameter("peixundidian");
		String peixunjihua = request.getParameter("peixunjihua");
		String peixunneirong = request.getParameter("peixunneirong");
		String peixunzhouqi = request.getParameter("peixunzhouqi");
		Peixun bean = peixunDao.selectBean(" where id= "+request.getParameter("id"));
		bean.setPeixundidian(peixundidian);
		bean.setPeixunjihua(peixunjihua);
		bean.setPeixunneirong(peixunneirong);
		bean.setPeixunzhouqi(peixunzhouqi);
		peixunDao.updateBean(bean);
		HttpServletResponse response = ServletActionContext.getResponse();
		response.setCharacterEncoding("gbk");response.setContentType("text/html; charset=gbk");
		response
				.getWriter()
				.print(
						"<script language=javascript>alert('操作成功');window.location.href='method!peixunlist';</script>");
	}

//查看培训信息操作
	public String peixunupdate3() {
		HttpServletRequest request = ServletActionContext.getRequest();
		Peixun bean = peixunDao.selectBean(" where id= "+request.getParameter("id"));
		request.setAttribute("bean", bean);
		this.setUrl("peixun/peixunupdate3.jsp");
		return SUCCESS;
	}
//删除培训信息操作
	public void peixundelete() throws IOException {
		HttpServletRequest request = ServletActionContext.getRequest();
		Peixun bean = peixunDao.selectBean(" where id= "+request.getParameter("id"));
		bean.setPeixunlock(1);
		peixunDao.updateBean(bean);
		HttpServletResponse response = ServletActionContext.getResponse();
		response.setCharacterEncoding("gbk");response.setContentType("text/html; charset=gbk");
		response
				.getWriter()
				.print(
						"<script language=javascript>alert('操作成功');window.location.href='method!peixunlist';</script>");
	}
	
	
	private ZhiweiDao zhiweiDao;


	public ZhiweiDao getZhiweiDao() {
		return zhiweiDao;
	}

	public void setZhiweiDao(ZhiweiDao zhiweiDao) {
		this.zhiweiDao = zhiweiDao;
	}
	
	//职位信息列表
	public String zhiweilist()  {
		HttpServletRequest request = ServletActionContext.getRequest();
		
		String zhiweimingchen = request.getParameter("zhiweimingchen");

		
		StringBuffer sb = new StringBuffer();
		sb.append(" where ");
		StringBuffer sb2 = new StringBuffer();
		sb2.append(" where ");

		if(zhiweimingchen!=null&&!"".equals(zhiweimingchen)){
			sb.append(" zhiweimingchen like '%"+zhiweimingchen+"%'");
			sb.append(" and ");
			sb2.append(" zhiweimingchen like '%"+zhiweimingchen+"%'");
			sb2.append(" and ");

			request.setAttribute("zhiweimingchen", zhiweimingchen);
		}
		
		
		sb.append(" zhiweilock=0 order by id desc");
		String where = sb.toString();
		sb2.append(" zhiweilock=0 ");
		String where2 = sb2.toString();
		
		int currentpage = 1;
		int pagesize =10;
		if(request.getParameter("pagenum")!=null){
			currentpage = Integer.parseInt(request.getParameter("pagenum"));
		}
		int total = zhiweiDao.selectBeanCount(where2);
		request.setAttribute("list", zhiweiDao.selectBeanList((currentpage - 1) * pagesize, pagesize, where));
		request.setAttribute("pagerinfo", Pager.getPagerNormal(total, pagesize,
				currentpage, "method!zhiweilist", "共有" + total + "条记录"));
		this.setUrl("zhiwei/zhiweilist.jsp");
		return SUCCESS;
	}

//跳转到添加职位信息页面
	public String zhiweiadd() {
		this.setUrl("zhiwei/zhiweiadd.jsp");
		return SUCCESS;
	}
//添加职位信息操作
	public void zhiweiadd2() throws IOException {
		HttpServletRequest request = ServletActionContext.getRequest();
		String beizhu = request.getParameter("beizhu");
		String zhaopinrenshi = request.getParameter("zhaopinrenshi");
		String zhiweimingchen = request.getParameter("zhiweimingchen");
		Zhiwei bean = new Zhiwei();
		bean.setBeizhu(beizhu);
		bean.setCreatetime(new Date());
		bean.setZhaopinrenshi(zhaopinrenshi);
		bean.setZhiweimingchen(zhiweimingchen);
		zhiweiDao.insertBean(bean);
	
		HttpServletResponse response = ServletActionContext.getResponse();
		response.setCharacterEncoding("gbk");response.setContentType("text/html; charset=gbk");
		response
				.getWriter()
				.print(
						"<script language=javascript>alert('操作成功');window.location.href='method!zhiweilist';</script>");

		
	}
//跳转到更新职位信息页面
	public String zhiweiupdate() {
		HttpServletRequest request = ServletActionContext.getRequest();
		
		Zhiwei bean = zhiweiDao.selectBean(" where id= "+request.getParameter("id"));
		request.setAttribute("bean", bean);
		this.setUrl("zhiwei/zhiweiupdate.jsp");
		return SUCCESS;
	}
//更新职位信息操作
	public void zhiweiupdate2() throws IOException {
		HttpServletRequest request = ServletActionContext.getRequest();
		String beizhu = request.getParameter("beizhu");
		String zhaopinrenshi = request.getParameter("zhaopinrenshi");
		String zhiweimingchen = request.getParameter("zhiweimingchen");
		Zhiwei bean = zhiweiDao.selectBean(" where id= "+request.getParameter("id"));
		bean.setBeizhu(beizhu);
		bean.setZhaopinrenshi(zhaopinrenshi);
		bean.setZhiweimingchen(zhiweimingchen);
		zhiweiDao.updateBean(bean);
		HttpServletResponse response = ServletActionContext.getResponse();
		response.setCharacterEncoding("gbk");response.setContentType("text/html; charset=gbk");
		response
				.getWriter()
				.print(
						"<script language=javascript>alert('操作成功');window.location.href='method!zhiweilist';</script>");
	}

//查看职位信息操作
	public String zhiweiupdate3() {
		HttpServletRequest request = ServletActionContext.getRequest();
		Zhiwei bean = zhiweiDao.selectBean(" where id= "+request.getParameter("id"));
		request.setAttribute("bean", bean);
		this.setUrl("zhiwei/zhiweiupdate3.jsp");
		return SUCCESS;
	}
//删除职位信息操作
	public void zhiweidelete() throws IOException {
		HttpServletRequest request = ServletActionContext.getRequest();
		Zhiwei bean = zhiweiDao.selectBean(" where id= "+request.getParameter("id"));
		bean.setZhiweilock(1);
		zhiweiDao.updateBean(bean);
		HttpServletResponse response = ServletActionContext.getResponse();
		response.setCharacterEncoding("gbk");response.setContentType("text/html; charset=gbk");
		response
				.getWriter()
				.print(
						"<script language=javascript>alert('操作成功');window.location.href='method!zhiweilist';</script>");
	}
	
	
	private ZhiweijiluDao zhiweijiluDao;


	public ZhiweijiluDao getZhiweijiluDao() {
		return zhiweijiluDao;
	}

	public void setZhiweijiluDao(ZhiweijiluDao zhiweijiluDao) {
		this.zhiweijiluDao = zhiweijiluDao;
	}
	
	//应聘记录信息列表
	public String zhiweijilulist()  {
		HttpServletRequest request = ServletActionContext.getRequest();
		
		String zhiweimingchen = request.getParameter("zhiweimingchen");

		
		StringBuffer sb = new StringBuffer();
		sb.append(" where ");
		StringBuffer sb2 = new StringBuffer();
		sb2.append(" where ");

		if(zhiweimingchen!=null&&!"".equals(zhiweimingchen)){
			sb.append(" zhiwei.zhiweimingchen like '%"+zhiweimingchen+"%'");
			sb.append(" and ");
			sb2.append(" zhiwei.zhiweimingchen like '%"+zhiweimingchen+"%'");
			sb2.append(" and ");

			request.setAttribute("zhiweimingchen", zhiweimingchen);
		}
		
		
		sb.append(" zhiweijilulock=0 order by id desc");
		String where = sb.toString();
		sb2.append(" zhiweijilulock=0 ");
		String where2 = sb2.toString();
		
		int currentpage = 1;
		int pagesize =10;
		if(request.getParameter("pagenum")!=null){
			currentpage = Integer.parseInt(request.getParameter("pagenum"));
		}
		int total = zhiweijiluDao.selectBeanCount(where2);
		request.setAttribute("list", zhiweijiluDao.selectBeanList((currentpage - 1) * pagesize, pagesize, where));
		request.setAttribute("pagerinfo", Pager.getPagerNormal(total, pagesize,
				currentpage, "method!zhiweijilulist", "共有" + total + "条记录"));
		this.setUrl("zhiweijilu/zhiweijilulist.jsp");
		return SUCCESS;
	}

//跳转到添加应聘记录信息页面
	public String zhiweijiluadd() {
		HttpServletRequest request = ServletActionContext.getRequest();
		request.setAttribute("list2", zhiweiDao.selectBeanList(0,999," where zhiweilock=0 "));
		this.setUrl("zhiweijilu/zhiweijiluadd.jsp");
		return SUCCESS;
	}
//添加应聘记录信息操作
	public void zhiweijiluadd2() throws IOException {
		HttpServletRequest request = ServletActionContext.getRequest();
		String addresss = request.getParameter("addresss");
		String lianxidianhua = request.getParameter("lianxidianhua");
		String xingbie = request.getParameter("xingbie");
		String xingming = request.getParameter("xingming");
		String yingpingjieguo = request.getParameter("yingpingjieguo");
		String xueli = request.getParameter("xueli");
		String zhiwei = request.getParameter("zhiwei");
		Zhiweijilu bean = new Zhiweijilu();
		bean.setAddresss(addresss);
		bean.setCreatetime(new Date());
		bean.setLianxidianhua(lianxidianhua);
		bean.setXingbie(xingbie);
		bean.setXingming(xingming);
		bean.setXueli(xueli);
		bean.setYingpingjieguo(yingpingjieguo);
		bean.setZhiwei(zhiweiDao.selectBean(" where id= "+zhiwei));
		zhiweijiluDao.insertBean(bean);
	
		HttpServletResponse response = ServletActionContext.getResponse();
		response.setCharacterEncoding("gbk");response.setContentType("text/html; charset=gbk");
		response
				.getWriter()
				.print(
						"<script language=javascript>alert('操作成功');window.location.href='method!zhiweijilulist';</script>");

		
	}
//跳转到更新应聘记录信息页面
	public String zhiweijiluupdate() {
		HttpServletRequest request = ServletActionContext.getRequest();
		request.setAttribute("list2", zhiweiDao.selectBeanList(0,999," where zhiweilock=0 "));
		Zhiweijilu bean = zhiweijiluDao.selectBean(" where id= "+request.getParameter("id"));
		request.setAttribute("bean", bean);
		this.setUrl("zhiweijilu/zhiweijiluupdate.jsp");
		return SUCCESS;
	}
//更新应聘记录信息操作
	public void zhiweijiluupdate2() throws IOException {
		HttpServletRequest request = ServletActionContext.getRequest();
		String addresss = request.getParameter("addresss");
		String lianxidianhua = request.getParameter("lianxidianhua");
		String xingbie = request.getParameter("xingbie");
		String xingming = request.getParameter("xingming");
		String yingpingjieguo = request.getParameter("yingpingjieguo");
		String xueli = request.getParameter("xueli");
		Zhiweijilu bean = zhiweijiluDao.selectBean(" where id= "+request.getParameter("id"));
		bean.setAddresss(addresss);
		bean.setLianxidianhua(lianxidianhua);
		bean.setXingbie(xingbie);
		bean.setXingming(xingming);
		bean.setXueli(xueli);
		bean.setYingpingjieguo(yingpingjieguo);
		zhiweijiluDao.updateBean(bean);
		HttpServletResponse response = ServletActionContext.getResponse();
		response.setCharacterEncoding("gbk");response.setContentType("text/html; charset=gbk");
		response
				.getWriter()
				.print(
						"<script language=javascript>alert('操作成功');window.location.href='method!zhiweijilulist';</script>");
	}

//查看应聘记录信息操作
	public String zhiweijiluupdate3() {
		HttpServletRequest request = ServletActionContext.getRequest();
		Zhiweijilu bean = zhiweijiluDao.selectBean(" where id= "+request.getParameter("id"));
		request.setAttribute("bean", bean);
		this.setUrl("zhiweijilu/zhiweijiluupdate3.jsp");
		return SUCCESS;
	}
//删除应聘记录信息操作
	public void zhiweijiludelete() throws IOException {
		HttpServletRequest request = ServletActionContext.getRequest();
		Zhiweijilu bean = zhiweijiluDao.selectBean(" where id= "+request.getParameter("id"));
		bean.setZhiweijilulock(1);
		zhiweijiluDao.updateBean(bean);
		HttpServletResponse response = ServletActionContext.getResponse();
		response.setCharacterEncoding("gbk");response.setContentType("text/html; charset=gbk");
		response
				.getWriter()
				.print(
						"<script language=javascript>alert('操作成功');window.location.href='method!zhiweijilulist';</script>");
	}
	
	
	//应聘者信息查询
	public String zhiweijilulist2()  {
		HttpServletRequest request = ServletActionContext.getRequest();
		
		String xingming = request.getParameter("xingming");

		
		StringBuffer sb = new StringBuffer();
		sb.append(" where ");
		StringBuffer sb2 = new StringBuffer();
		sb2.append(" where ");

		if(xingming!=null&&!"".equals(xingming)){
			sb.append(" xingming like '%"+xingming+"%'");
			sb.append(" and ");
			sb2.append(" xingming like '%"+xingming+"%'");
			sb2.append(" and ");

			request.setAttribute("xingming", xingming);
		}
		
		
		sb.append(" zhiweijilulock=0 order by id desc");
		String where = sb.toString();
		sb2.append(" zhiweijilulock=0 ");
		String where2 = sb2.toString();
		
		int currentpage = 1;
		int pagesize =10;
		if(request.getParameter("pagenum")!=null){
			currentpage = Integer.parseInt(request.getParameter("pagenum"));
		}
		int total = zhiweijiluDao.selectBeanCount(where2);
		request.setAttribute("list", zhiweijiluDao.selectBeanList((currentpage - 1) * pagesize, pagesize, where));
		request.setAttribute("pagerinfo", Pager.getPagerNormal(total, pagesize,
				currentpage, "method!zhiweijilulist2", "共有" + total + "条记录"));
		this.setUrl("zhiweijilu/zhiweijilulist2.jsp");
		return SUCCESS;
	}
	
	
	private KaoqinDao kaoqinDao;


	public KaoqinDao getKaoqinDao() {
		return kaoqinDao;
	}

	public void setKaoqinDao(KaoqinDao kaoqinDao) {
		this.kaoqinDao = kaoqinDao;
	}
	
	
	//考勤列表
	public String kaoqinlist()  {
		HttpServletRequest request = ServletActionContext.getRequest();
		request.setAttribute("list2", yuangongDao.selectBeanList(0, 9999, "  where yuangonglock=0 " ));
		
		
		String leixing = request.getParameter("leixing");

		
		StringBuffer sb = new StringBuffer();
		sb.append(" where ");
		StringBuffer sb2 = new StringBuffer();
		sb2.append(" where ");

		
		
		if(leixing!=null&&!"".equals(leixing)){
			sb.append("leixing like '%"+leixing+"%'");
			sb.append(" and ");
			sb2.append("leixing like '%"+leixing+"%'");
			sb2.append(" and ");
			request.setAttribute("leixing", leixing);
		}

		
		sb.append(" kaoqinlock=0 order by id desc");
		String where = sb.toString();
		sb2.append(" kaoqinlock=0 ");
		String where2 = sb2.toString();
		int currentpage = 1;
		int pagesize =20;
		if(request.getParameter("pagenum")!=null){
			currentpage = Integer.parseInt(request.getParameter("pagenum"));
		}
		int total = kaoqinDao.selectBeanCount(where2);
		request.setAttribute("list", kaoqinDao.selectBeanList((currentpage - 1) * pagesize, pagesize, where));
		request.setAttribute("pagerinfo", Pager.getPagerNormal(total, pagesize,
				currentpage, "method!kaoqinlist", "共有" + total + "条记录"));
		this.setUrl("kaoqin/kaoqinlist.jsp");
		return SUCCESS;
	}

//跳转到添加考勤页面
	public String kaoqinadd() {
		HttpServletRequest request = ServletActionContext.getRequest();
		request.setAttribute("list2", yuangongDao.selectBeanList(0, 9999, "  where yuangonglock=0 " ));
		this.setUrl("kaoqin/kaoqinadd.jsp");
		return SUCCESS;
	}
	//添加考勤操作
	public void kaoqinadd2() throws IOException {
		HttpServletRequest request = ServletActionContext.getRequest();
		String beizhu = request.getParameter("beizhu");
		String leixing = request.getParameter("leixing");
		String yuangong = request.getParameter("yuangong");
		Kaoqin bean = new Kaoqin();
		bean.setBeizhu(beizhu);
		bean.setLeixing(leixing);
		bean.setYuangong(yuangongDao.selectBean(" where id= "+yuangong));
		bean.setCreatetime(new Date());
		kaoqinDao.insertBean(bean);
		HttpServletResponse response = ServletActionContext.getResponse();
		response.setCharacterEncoding("gbk");response.setContentType("text/html; charset=gbk");
		response
		.getWriter()
		.print(
				"<script language=javascript>alert('操作成功');window.location.href='method!kaoqinlist';</script>");
		
		

		
	}
	//跳转到更新考勤信息页面
	public String kaoqinupdate() {
		HttpServletRequest request = ServletActionContext.getRequest();
		Kaoqin bean = kaoqinDao.selectBean(" where id= "+request.getParameter("id"));
		request.setAttribute("bean", bean);
		this.setUrl("kaoqin/kaoqinupdate.jsp");
		return SUCCESS;
	}
//更新考勤信息操作
	public void kaoqinupdate2() throws IOException {
		HttpServletRequest request = ServletActionContext.getRequest();
		String beizhu = request.getParameter("beizhu");
	
		Kaoqin bean = kaoqinDao.selectBean(" where id= "+request.getParameter("id"));
		bean.setBeizhu(beizhu);
		kaoqinDao.updateBean(bean);
		HttpServletResponse response = ServletActionContext.getResponse();
		response.setCharacterEncoding("gbk");response.setContentType("text/html; charset=gbk");
		response
		.getWriter()
		.print(
				"<script language=javascript>alert('操作成功');window.location.href='method!kaoqinlist';</script>");

	}
	
//查看考勤信息操作
	public String kaoqinupdate3() {
		HttpServletRequest request = ServletActionContext.getRequest();
		Kaoqin bean = kaoqinDao.selectBean(" where id= "+request.getParameter("id"));
		request.setAttribute("bean", bean);
		this.setUrl("kaoqin/kaoqinupdate3.jsp");
		return SUCCESS;
	}
//删除考勤信息操作
	public void kaoqindelete() throws IOException {
		HttpServletRequest request = ServletActionContext.getRequest();
		Kaoqin bean = kaoqinDao.selectBean(" where id= "+request.getParameter("id"));
		bean.setKaoqinlock(1);
		kaoqinDao.updateBean(bean);
		HttpServletResponse response = ServletActionContext.getResponse();
		response.setCharacterEncoding("gbk");response.setContentType("text/html; charset=gbk");
		response
		.getWriter()
		.print(
				"<script language=javascript>alert('操作成功');window.location.href='method!kaoqinlist';</script>");
		
		
	}
	
	private GongziDao gongziDao;


	public GongziDao getGongziDao() {
		return gongziDao;
	}

	public void setGongziDao(GongziDao gongziDao) {
		this.gongziDao = gongziDao;
	}
	
	
	
	//工资信息列表
	public String gongzilist()  {
		HttpServletRequest request = ServletActionContext.getRequest();
		
		String truename = request.getParameter("truename");

		
		StringBuffer sb = new StringBuffer();
		sb.append(" where ");
		StringBuffer sb2 = new StringBuffer();
		sb2.append(" where ");

		if(truename!=null&&!"".equals(truename)){
			sb.append(" yuangong.truename like '%"+truename+"%'");
			sb.append(" and ");
			sb2.append(" yuangong.truename like '%"+truename+"%'");
			sb2.append(" and ");

			request.setAttribute("truename", truename);
		}
		
		
		sb.append(" gongzilock=0 order by id desc");
		String where = sb.toString();
		sb2.append(" gongzilock=0 ");
		String where2 = sb2.toString();
		
		int currentpage = 1;
		int pagesize =10;
		if(request.getParameter("pagenum")!=null){
			currentpage = Integer.parseInt(request.getParameter("pagenum"));
		}
		int total = gongziDao.selectBeanCount(where2);
		request.setAttribute("list", gongziDao.selectBeanList((currentpage - 1) * pagesize, pagesize, where));
		request.setAttribute("pagerinfo", Pager.getPagerNormal(total, pagesize,
				currentpage, "method!gongzilist", "共有" + total + "条记录"));
		this.setUrl("gongzi/gongzilist.jsp");
		return SUCCESS;
	}

//跳转到添加工资信息页面
	public String gongziadd() {
		HttpServletRequest request = ServletActionContext.getRequest();
		request.setAttribute("list2", yuangongDao.selectBeanList(0,999," where yuangonglock=0 "));
		this.setUrl("gongzi/gongziadd.jsp");
		return SUCCESS;
	}
//添加工资信息操作
	public void gongziadd2() throws IOException {
		HttpServletRequest request = ServletActionContext.getRequest();
		String gongzi = request.getParameter("gongzi");
		String yuefen = request.getParameter("yuefen");
		String yuangong = request.getParameter("yuangong");
		Gongzi bean = new Gongzi();
		bean.setCreatetime(new Date());
		bean.setGongzi(gongzi);
		bean.setYuefen(yuefen);
		bean.setYuangong(yuangongDao.selectBean(" where id= "+yuangong));
	
		gongziDao.insertBean(bean);
	
		HttpServletResponse response = ServletActionContext.getResponse();
		response.setCharacterEncoding("gbk");response.setContentType("text/html; charset=gbk");
		response
				.getWriter()
				.print(
						"<script language=javascript>alert('操作成功');window.location.href='method!gongzilist';</script>");

		
	}
//跳转到更新工资信息页面
	public String gongziupdate() {
		HttpServletRequest request = ServletActionContext.getRequest();
		request.setAttribute("list2", yuangongDao.selectBeanList(0,999," where yuangonglock=0 "));
		Gongzi bean = gongziDao.selectBean(" where id= "+request.getParameter("id"));
		request.setAttribute("bean", bean);
		this.setUrl("gongzi/gongziupdate.jsp");
		return SUCCESS;
	}
//更新工资信息操作
	public void gongziupdate2() throws IOException {
		HttpServletRequest request = ServletActionContext.getRequest();
		String gongzi = request.getParameter("gongzi");
		Gongzi bean = gongziDao.selectBean(" where id= "+request.getParameter("id"));
		bean.setGongzi(gongzi);
		gongziDao.updateBean(bean);
		HttpServletResponse response = ServletActionContext.getResponse();
		response.setCharacterEncoding("gbk");response.setContentType("text/html; charset=gbk");
		response
				.getWriter()
				.print(
						"<script language=javascript>alert('操作成功');window.location.href='method!gongzilist';</script>");
	}

//查看工资信息操作
	public String gongziupdate3() {
		HttpServletRequest request = ServletActionContext.getRequest();
		Gongzi bean = gongziDao.selectBean(" where id= "+request.getParameter("id"));
		request.setAttribute("bean", bean);
		this.setUrl("gongzi/gongziupdate3.jsp");
		return SUCCESS;
	}
//删除工资信息操作
	public void gongzidelete() throws IOException {
		HttpServletRequest request = ServletActionContext.getRequest();
		Gongzi bean = gongziDao.selectBean(" where id= "+request.getParameter("id"));
		bean.setGongzilock(1);
		gongziDao.updateBean(bean);
		HttpServletResponse response = ServletActionContext.getResponse();
		response.setCharacterEncoding("gbk");response.setContentType("text/html; charset=gbk");
		response
				.getWriter()
				.print(
						"<script language=javascript>alert('操作成功');window.location.href='method!gongzilist';</script>");
	}
	
	private BaoxianfuliDao baoxianfuliDao;


	public BaoxianfuliDao getBaoxianfuliDao() {
		return baoxianfuliDao;
	}

	public void setBaoxianfuliDao(BaoxianfuliDao baoxianfuliDao) {
		this.baoxianfuliDao = baoxianfuliDao;
	}
	
	
	//保险福利信息列表
	public String baoxianfulilist()  {
		HttpServletRequest request = ServletActionContext.getRequest();
		
		String truename = request.getParameter("truename");

		
		StringBuffer sb = new StringBuffer();
		sb.append(" where ");
		StringBuffer sb2 = new StringBuffer();
		sb2.append(" where ");

		if(truename!=null&&!"".equals(truename)){
			sb.append(" yuangong.truename like '%"+truename+"%'");
			sb.append(" and ");
			sb2.append(" yuangong.truename like '%"+truename+"%'");
			sb2.append(" and ");

			request.setAttribute("truename", truename);
		}
		
		
		sb.append(" baoxianfulilock=0 order by id desc");
		String where = sb.toString();
		sb2.append(" baoxianfulilock=0 ");
		String where2 = sb2.toString();
		
		int currentpage = 1;
		int pagesize =10;
		if(request.getParameter("pagenum")!=null){
			currentpage = Integer.parseInt(request.getParameter("pagenum"));
		}
		int total = baoxianfuliDao.selectBeanCount(where2);
		request.setAttribute("list", baoxianfuliDao.selectBeanList((currentpage - 1) * pagesize, pagesize, where));
		request.setAttribute("pagerinfo", Pager.getPagerNormal(total, pagesize,
				currentpage, "method!baoxianfulilist", "共有" + total + "条记录"));
		this.setUrl("baoxianfuli/baoxianfulilist.jsp");
		return SUCCESS;
	}

//跳转到添加保险福利信息页面
	public String baoxianfuliadd() {
		HttpServletRequest request = ServletActionContext.getRequest();
		request.setAttribute("list2", yuangongDao.selectBeanList(0,999," where yuangonglock=0 "));
		this.setUrl("baoxianfuli/baoxianfuliadd.jsp");
		return SUCCESS;
	}
//添加保险福利信息操作
	public void baoxianfuliadd2() throws IOException {
		HttpServletRequest request = ServletActionContext.getRequest();
		String gongzi = request.getParameter("gongzi");
		String yuefen = request.getParameter("yuefen");
		String yuangong = request.getParameter("yuangong");
		String mingchen = request.getParameter("mingchen");
		Baoxianfuli bean = new Baoxianfuli();
		bean.setMingchen(mingchen);
		bean.setCreatetime(new Date());
		bean.setGongzi(gongzi);
		bean.setYuefen(yuefen);
		bean.setYuangong(yuangongDao.selectBean(" where id= "+yuangong));
	
		baoxianfuliDao.insertBean(bean);
	
		HttpServletResponse response = ServletActionContext.getResponse();
		response.setCharacterEncoding("gbk");response.setContentType("text/html; charset=gbk");
		response
				.getWriter()
				.print(
						"<script language=javascript>alert('操作成功');window.location.href='method!baoxianfulilist';</script>");

		
	}
//跳转到更新保险福利信息页面
	public String baoxianfuliupdate() {
		HttpServletRequest request = ServletActionContext.getRequest();
		request.setAttribute("list2", yuangongDao.selectBeanList(0,999," where yuangonglock=0 "));
		Baoxianfuli bean = baoxianfuliDao.selectBean(" where id= "+request.getParameter("id"));
		request.setAttribute("bean", bean);
		this.setUrl("baoxianfuli/baoxianfuliupdate.jsp");
		return SUCCESS;
	}
//更新保险福利信息操作
	public void baoxianfuliupdate2() throws IOException {
		HttpServletRequest request = ServletActionContext.getRequest();
		String gongzi = request.getParameter("gongzi");
		Baoxianfuli bean = baoxianfuliDao.selectBean(" where id= "+request.getParameter("id"));
		bean.setGongzi(gongzi);
		baoxianfuliDao.updateBean(bean);
		HttpServletResponse response = ServletActionContext.getResponse();
		response.setCharacterEncoding("gbk");response.setContentType("text/html; charset=gbk");
		response
				.getWriter()
				.print(
						"<script language=javascript>alert('操作成功');window.location.href='method!baoxianfulilist';</script>");
	}

//查看保险福利信息操作
	public String baoxianfuliupdate3() {
		HttpServletRequest request = ServletActionContext.getRequest();
		Baoxianfuli bean = baoxianfuliDao.selectBean(" where id= "+request.getParameter("id"));
		request.setAttribute("bean", bean);
		this.setUrl("baoxianfuli/baoxianfuliupdate3.jsp");
		return SUCCESS;
	}
//删除保险福利信息操作
	public void baoxianfulidelete() throws IOException {
		HttpServletRequest request = ServletActionContext.getRequest();
		Baoxianfuli bean = baoxianfuliDao.selectBean(" where id= "+request.getParameter("id"));
		bean.setBaoxianfulilock(1);
		baoxianfuliDao.updateBean(bean);
		HttpServletResponse response = ServletActionContext.getResponse();
		response.setCharacterEncoding("gbk");response.setContentType("text/html; charset=gbk");
		response
				.getWriter()
				.print(
						"<script language=javascript>alert('操作成功');window.location.href='method!baoxianfulilist';</script>");
	}
	
	
	//系统账号管理
	public String userlist()  {
		HttpServletRequest request = ServletActionContext.getRequest();
		request.setAttribute("list2", bumenDao.selectBeanList(0,999," where bumenlock=0 "));
		
		String truename = request.getParameter("truename");

		
		StringBuffer sb = new StringBuffer();
		sb.append(" where ");
		StringBuffer sb2 = new StringBuffer();
		sb2.append(" where ");

		if(truename!=null&&!"".equals(truename)){
			sb.append("truename like '%"+truename+"%'");
			sb.append(" and ");
			sb2.append("truename like '%"+truename+"%'");
			sb2.append(" and ");

			request.setAttribute("truename", truename);
		}
		
		
		sb.append(" userlock=0 and role=0 order by id desc");
		String where = sb.toString();
		sb2.append(" userlock=0 and role=0");
		String where2 = sb2.toString();
		
		int currentpage = 1;
		int pagesize =10;
		if(request.getParameter("pagenum")!=null){
			currentpage = Integer.parseInt(request.getParameter("pagenum"));
		}
		int total = userDao.selectBeanCount(where2);
		request.setAttribute("list", userDao.selectBeanList((currentpage - 1) * pagesize, pagesize, where));
		request.setAttribute("pagerinfo", Pager.getPagerNormal(total, pagesize,
				currentpage, "method!userlist", "共有" + total + "条记录"));
		this.setUrl("user/userlist.jsp");
		return SUCCESS;
	}
	
	
	public String yuangonglist20()  {
		HttpServletRequest request = ServletActionContext.getRequest();
		request.setAttribute("list2", bumenDao.selectBeanList(0,999," where bumenlock=0 "));
		
		String truename = request.getParameter("truename");
		String yuangonghao = request.getParameter("yuangonghao");
		String bumen = request.getParameter("bumen");
		
		StringBuffer sb = new StringBuffer();
		sb.append(" where ");
		StringBuffer sb2 = new StringBuffer();
		sb2.append(" where ");

		if(truename!=null&&!"".equals(truename)){
			sb.append("truename like '%"+truename+"%'");
			sb.append(" and ");
			sb2.append("truename like '%"+truename+"%'");
			sb2.append(" and ");

			request.setAttribute("truename", truename);
		}
		
		if(yuangonghao!=null&&!"".equals(yuangonghao)){
			sb.append("yuangonghao like '%"+yuangonghao+"%'");
			sb.append(" and ");
			sb2.append("yuangonghao like '%"+yuangonghao+"%'");
			sb2.append(" and ");

			request.setAttribute("yuangonghao", yuangonghao);
		}
		
		if(bumen!=null&&!"".equals(bumen)){
			sb.append("bumen.name like '%"+bumen+"%'");
			sb.append(" and ");
			sb2.append("bumen.name like '%"+bumen+"%'");
			sb2.append(" and ");

			request.setAttribute("bumen", bumen);
		}
		HttpSession session = request.getSession();
		User user = (User)session.getAttribute("user");
		
		sb.append(" yuangonglock=0 and yuangonghao='"+user.getUsername()+"' order by id desc");
		String where = sb.toString();
		sb2.append(" yuangonglock=0 and yuangonghao='"+user.getUsername()+"'");
		String where2 = sb2.toString();
		
		int currentpage = 1;
		int pagesize =10;
		if(request.getParameter("pagenum")!=null){
			currentpage = Integer.parseInt(request.getParameter("pagenum"));
		}
		int total = yuangongDao.selectBeanCount(where2);
		request.setAttribute("list", yuangongDao.selectBeanList((currentpage - 1) * pagesize, pagesize, where));
		request.setAttribute("pagerinfo", Pager.getPagerNormal(total, pagesize,
				currentpage, "method!yuangonglist20", "共有" + total + "条记录"));
		this.setUrl("yuangong/yuangonglist20.jsp");
		return SUCCESS;
	}
	
	
	//合同查询
	public String hetonglist2()  {
		HttpServletRequest request = ServletActionContext.getRequest();
		
		String truename = request.getParameter("truename");
		
		StringBuffer sb = new StringBuffer();
		sb.append(" where ");
		StringBuffer sb2 = new StringBuffer();
		sb2.append(" where ");

		if(truename!=null&&!"".equals(truename)){
			sb.append("yuangong.truename like '%"+truename+"%'");
			sb.append(" and ");
			sb2.append("yuangong.truename like '%"+truename+"%'");
			sb2.append(" and ");

			request.setAttribute("truename", truename);
		}
		
		HttpSession session = request.getSession();
		User user = (User)session.getAttribute("user");
		sb.append(" hetonglock=0 and yuangong.yuangonghao='"+user.getUsername()+"'  order by id desc");
		String where = sb.toString();
		sb2.append(" hetonglock=0 and yuangong.yuangonghao='"+user.getUsername()+"'");
		String where2 = sb2.toString();
		
		int currentpage = 1;
		int pagesize =10;
		if(request.getParameter("pagenum")!=null){
			currentpage = Integer.parseInt(request.getParameter("pagenum"));
		}
		int total = hetongDao.selectBeanCount(where2);
		request.setAttribute("list", hetongDao.selectBeanList((currentpage - 1) * pagesize, pagesize, where));
		request.setAttribute("pagerinfo", Pager.getPagerNormal(total, pagesize,
				currentpage, "method!hetonglist2", "共有" + total + "条记录"));
		this.setUrl("hetong/hetonglist2.jsp");
		return SUCCESS;
	}
	
	//培训查询
	public String peixunlist2()  {
		HttpServletRequest request = ServletActionContext.getRequest();
		
		String truename = request.getParameter("truename");

		
		StringBuffer sb = new StringBuffer();
		sb.append(" where ");
		StringBuffer sb2 = new StringBuffer();
		sb2.append(" where ");

		if(truename!=null&&!"".equals(truename)){
			sb.append(" yuangong.truename like '%"+truename+"%'");
			sb.append(" and ");
			sb2.append(" yuangong.truename like '%"+truename+"%'");
			sb2.append(" and ");

			request.setAttribute("truename", truename);
		}
		
		HttpSession session = request.getSession();
		User user = (User)session.getAttribute("user");
		sb.append(" peixunlock=0 and yuangong.yuangonghao='"+user.getUsername()+"'  order by id desc");
		String where = sb.toString();
		sb2.append(" peixunlock=0 and yuangong.yuangonghao='"+user.getUsername()+"'");
		String where2 = sb2.toString();
		
		int currentpage = 1;
		int pagesize =10;
		if(request.getParameter("pagenum")!=null){
			currentpage = Integer.parseInt(request.getParameter("pagenum"));
		}
		int total = peixunDao.selectBeanCount(where2);
		request.setAttribute("list", peixunDao.selectBeanList((currentpage - 1) * pagesize, pagesize, where));
		request.setAttribute("pagerinfo", Pager.getPagerNormal(total, pagesize,
				currentpage, "method!peixunlist2", "共有" + total + "条记录"));
		this.setUrl("peixun/peixunlist2.jsp");
		return SUCCESS;
	}
	
	//职位信息列表
	public String zhiweilist2()  {
		HttpServletRequest request = ServletActionContext.getRequest();
		
		String zhiweimingchen = request.getParameter("zhiweimingchen");

		
		StringBuffer sb = new StringBuffer();
		sb.append(" where ");
		StringBuffer sb2 = new StringBuffer();
		sb2.append(" where ");

		if(zhiweimingchen!=null&&!"".equals(zhiweimingchen)){
			sb.append(" zhiweimingchen like '%"+zhiweimingchen+"%'");
			sb.append(" and ");
			sb2.append(" zhiweimingchen like '%"+zhiweimingchen+"%'");
			sb2.append(" and ");

			request.setAttribute("zhiweimingchen", zhiweimingchen);
		}
		
		
		sb.append(" zhiweilock=0 order by id desc");
		String where = sb.toString();
		sb2.append(" zhiweilock=0 ");
		String where2 = sb2.toString();
		
		int currentpage = 1;
		int pagesize =10;
		if(request.getParameter("pagenum")!=null){
			currentpage = Integer.parseInt(request.getParameter("pagenum"));
		}
		int total = zhiweiDao.selectBeanCount(where2);
		request.setAttribute("list", zhiweiDao.selectBeanList((currentpage - 1) * pagesize, pagesize, where));
		request.setAttribute("pagerinfo", Pager.getPagerNormal(total, pagesize,
				currentpage, "method!zhiweilist2", "共有" + total + "条记录"));
		this.setUrl("zhiwei/zhiweilist2.jsp");
		return SUCCESS;
	}
	
	

	//考勤列表
	public String kaoqinlist2()  {
		HttpServletRequest request = ServletActionContext.getRequest();
		request.setAttribute("list2", yuangongDao.selectBeanList(0, 9999, "  where yuangonglock=0 " ));
		
		
		String leixing = request.getParameter("leixing");

		
		StringBuffer sb = new StringBuffer();
		sb.append(" where ");
		StringBuffer sb2 = new StringBuffer();
		sb2.append(" where ");

		
		
		if(leixing!=null&&!"".equals(leixing)){
			sb.append("leixing like '%"+leixing+"%'");
			sb.append(" and ");
			sb2.append("leixing like '%"+leixing+"%'");
			sb2.append(" and ");
			request.setAttribute("leixing", leixing);
		}

		HttpSession session = request.getSession();
		User user = (User)session.getAttribute("user");
		
		sb.append(" kaoqinlock=0 and yuangong.yuangonghao='"+user.getUsername()+"' order by id desc");
		String where = sb.toString();
		sb2.append(" kaoqinlock=0 and yuangong.yuangonghao='"+user.getUsername()+"'");
		String where2 = sb2.toString();
		int currentpage = 1;
		int pagesize =20;
		if(request.getParameter("pagenum")!=null){
			currentpage = Integer.parseInt(request.getParameter("pagenum"));
		}
		int total = kaoqinDao.selectBeanCount(where2);
		request.setAttribute("list", kaoqinDao.selectBeanList((currentpage - 1) * pagesize, pagesize, where));
		request.setAttribute("pagerinfo", Pager.getPagerNormal(total, pagesize,
				currentpage, "method!kaoqinlist2", "共有" + total + "条记录"));
		this.setUrl("kaoqin/kaoqinlist2.jsp");
		return SUCCESS;
	}
	
	
	//工资信息列表
	public String gongzilist2()  {
		HttpServletRequest request = ServletActionContext.getRequest();
		
		String truename = request.getParameter("truename");

		
		StringBuffer sb = new StringBuffer();
		sb.append(" where ");
		StringBuffer sb2 = new StringBuffer();
		sb2.append(" where ");

		if(truename!=null&&!"".equals(truename)){
			sb.append(" yuangong.truename like '%"+truename+"%'");
			sb.append(" and ");
			sb2.append(" yuangong.truename like '%"+truename+"%'");
			sb2.append(" and ");

			request.setAttribute("truename", truename);
		}
		
		
		HttpSession session = request.getSession();
		User user = (User)session.getAttribute("user");
		sb.append(" gongzilock=0 and yuangong.yuangonghao='"+user.getUsername()+"' order by id desc");
		String where = sb.toString();
		sb2.append(" gongzilock=0 and yuangong.yuangonghao='"+user.getUsername()+"'");
		String where2 = sb2.toString();
		
		int currentpage = 1;
		int pagesize =10;
		if(request.getParameter("pagenum")!=null){
			currentpage = Integer.parseInt(request.getParameter("pagenum"));
		}
		int total = gongziDao.selectBeanCount(where2);
		request.setAttribute("list", gongziDao.selectBeanList((currentpage - 1) * pagesize, pagesize, where));
		request.setAttribute("pagerinfo", Pager.getPagerNormal(total, pagesize,
				currentpage, "method!gongzilist2", "共有" + total + "条记录"));
		this.setUrl("gongzi/gongzilist2.jsp");
		return SUCCESS;
	}
	
	
	//保险福利信息列表
	public String baoxianfulilist2()  {
		HttpServletRequest request = ServletActionContext.getRequest();
		
		String truename = request.getParameter("truename");

		
		StringBuffer sb = new StringBuffer();
		sb.append(" where ");
		StringBuffer sb2 = new StringBuffer();
		sb2.append(" where ");

		if(truename!=null&&!"".equals(truename)){
			sb.append(" yuangong.truename like '%"+truename+"%'");
			sb.append(" and ");
			sb2.append(" yuangong.truename like '%"+truename+"%'");
			sb2.append(" and ");

			request.setAttribute("truename", truename);
		}
		
		HttpSession session = request.getSession();
		User user = (User)session.getAttribute("user");
		sb.append(" baoxianfulilock=0 and yuangong.yuangonghao='"+user.getUsername()+"'  order by id desc");
		String where = sb.toString();
		sb2.append(" baoxianfulilock=0 and yuangong.yuangonghao='"+user.getUsername()+"'");
		String where2 = sb2.toString();
		
		int currentpage = 1;
		int pagesize =10;
		if(request.getParameter("pagenum")!=null){
			currentpage = Integer.parseInt(request.getParameter("pagenum"));
		}
		int total = baoxianfuliDao.selectBeanCount(where2);
		request.setAttribute("list", baoxianfuliDao.selectBeanList((currentpage - 1) * pagesize, pagesize, where));
		request.setAttribute("pagerinfo", Pager.getPagerNormal(total, pagesize,
				currentpage, "method!baoxianfulilist2", "共有" + total + "条记录"));
		this.setUrl("baoxianfuli/baoxianfulilist2.jsp");
		return SUCCESS;
	}

	public MenmianDao getMenmianDao() {
		return menmianDao;
	}

	public void setMenmianDao(MenmianDao menmianDao) {
		this.menmianDao = menmianDao;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getPrebumenid() {
		return prebumenid;
	}

	public void setPrebumenid(String prebumenid) {
		this.prebumenid = prebumenid;
	}
}
