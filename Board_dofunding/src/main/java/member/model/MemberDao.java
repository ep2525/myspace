package member.model;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.RowBounds;
import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import utility.Paging;

@Component("myMemberDao")
public class MemberDao {
private String namespace="member.model.Member";
	
	@Autowired
	SqlSessionTemplate sqlSessionTemplate;
	
	public int insertMember(MemberBean member) {
		int cnt=-1;
		MemberBean sbean = sqlSessionTemplate.selectOne(namespace+".SearchId",member.getId());
		if(sbean != null) {
			cnt=-2;
		}
		else {
			cnt=sqlSessionTemplate.insert(namespace+".InsertMember",member);			
		}
		return cnt;
	}

	public int totalCount(Map<String,String> map) {
		int cnt = sqlSessionTemplate.selectOne(namespace+".GetTotalCount",map);
		return cnt;
	}
	
	public List<MemberBean> memberList(Paging pageInfo,Map<String,String> map) {
		List<MemberBean> list = new ArrayList<MemberBean>();
		RowBounds rowBounds = new RowBounds(pageInfo.getOffset(),pageInfo.getLimit());
		list = sqlSessionTemplate.selectList(namespace+".GetMemberList",map,rowBounds);
		return list;
	}

	public MemberBean searchId(String id) {
		MemberBean sbean= null;
		sbean = sqlSessionTemplate.selectOne(namespace+".SearchId",id);
		return sbean;

	}

	public void mpointUpdate(String id, int pt) {
		MemberBean mb=new MemberBean();
		mb.setId(id);
		mb.setMpoint(pt);
		sqlSessionTemplate.update(namespace+".MpointUpdate",mb);
	}

}
