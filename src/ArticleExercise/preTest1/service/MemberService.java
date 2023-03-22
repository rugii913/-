package ArticleExercise.preTest1.service;

import ArticleExercise.preTest1.container.Container;
import ArticleExercise.preTest1.dao.MemberDao;
import ArticleExercise.preTest1.dto.Member;

import java.util.List;

public class MemberService {
    private MemberDao memberDao;

    public MemberService() {
        this.memberDao = Container.memberDao;
    }

    public int setNewId() {
        return memberDao.setNewId();
    }

    public Member getMemberByLoginId(String loginId) {
        return memberDao.getMemberByLoginId(loginId);
    }

    public List<Member> getMembers() {
        return memberDao.getMembers();
    }

    public boolean isJoinableLoginId(String loginId) {
        return memberDao.isJoinableLoginId(loginId);
    }

    public void add(Member member) {
        memberDao.add(member);
    }
}
