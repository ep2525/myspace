package board.model;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.RowBounds;
import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import utility.Paging;

@Component("myBoardDao")
public class BoardDao {

	private String namespace="board.model.Board";
	
	@Autowired
	private SqlSessionTemplate sqlSessionTemplate;
	
	
	public int insertArticle(BoardBean article){
		int cnt=-1;
		cnt=sqlSessionTemplate.insert(namespace+".InsertArticle", article);
		return cnt;
	}//insertArticle
	
	public int deleteArticle(BoardBean article, String passwd){
		int cnt=-1;
		passwd=sqlSessionTemplate.selectOne(namespace+".GetArticlePasswd", article);
		if(passwd.equals(article.getPasswd())) {
			cnt=sqlSessionTemplate.delete(namespace+".DeleteArticle", article);			
		}
		else {
			cnt=-2;
		}
		System.out.println(cnt);
		return cnt;
	}//deleteArticle
	
	//답글
	public int replyArticle(BoardBean article){
		int cnt=-1;
		//하나씩 증가시키는 업데이트필요.
		sqlSessionTemplate.update(namespace+".ReplyPlus", article);			
		cnt=sqlSessionTemplate.insert(namespace+".ReplyArticle", article);
		//sql="update board set re_step=re_step+1 where ref=? and re_step>?";
		//그럼 부모 다음값은 이번 글이 차지하게 됨. 이걸 insert전에 무조건 해줘야됌.
		return cnt;
	}//replyArticle
	
	
	public int updateArticle(BoardBean article){
		int cnt=-1;
		String passwd=sqlSessionTemplate.selectOne(namespace+".GetArticlePasswd", article);
		if(passwd.equals(article.getPasswd())) {
			cnt=sqlSessionTemplate.update(namespace+".UpdateArticle", article);			
		}
		else {
			cnt=-2;
		}
		System.out.println(cnt);
		return cnt;
	}//updateArticle
	
	//detail
	public BoardBean getArticle(BoardBean article){
		BoardBean detail=null;
		//조회수
		sqlSessionTemplate.update(namespace+".UpdateReadCount", article);
		//content내용
		detail=oneSelect(article);
		return detail;
	}//getArticle	
	
	public BoardBean oneSelect(BoardBean article){
		BoardBean detail=null;
		detail=sqlSessionTemplate.selectOne(namespace+".GetArticle", article);
		return detail;
	}//oneSelect	

	public List<BoardBean> getArticles(Paging pageInfo,Map<String, String> map){
		RowBounds rowBounds =new RowBounds(pageInfo.getOffset(),pageInfo.getLimit());
		List<BoardBean> articleList= sqlSessionTemplate.selectList(namespace+".GetArticles", map, rowBounds);		
		
		return articleList;
	}//getArticles
	
	public int getArticleCount(Map<String, String> map){
		int totalCount=0;
		totalCount=sqlSessionTemplate.selectOne(namespace+".GetArticleCount", map);
		return totalCount;
	}//getArticleCount
	
	
}
