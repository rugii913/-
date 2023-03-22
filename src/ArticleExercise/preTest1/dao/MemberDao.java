package ArticleExercise.preTest1.dao;

import ArticleExercise.preTest1.dto.Member;

import java.util.ArrayList;
import java.util.List;

public class MemberDao extends Dao{
    private List<Member> members;

    public MemberDao() {
        members = new ArrayList<>();
    }

    @Override
    public int getLastId() {
        return lastId;
    }

    public int setNewId() {
        return lastId + 1;
    }

    public int getMemberIndexByLoginId(String loginId) {
        int i = 0;
        for (Member member : members) {
            if (member.getLoginId().equals(loginId)) {
                return i;
            }
            i++;
        }
        return -1;
    }

    public Member getMemberByLoginId(String loginId) {
        int index = getMemberIndexByLoginId(loginId);
        if (index == -1) {
            return null;
        }
        return members.get(index);
    }

    public boolean isJoinableLoginId(String loginId) {
        int index = getMemberIndexByLoginId(loginId);
        if (index == -1) {
            return true;
        }
        return false;
    }

    public List<Member> getMembers() {
        return members;
    }

    public void add(Member member) {
        members.add(member);
        lastId++;
    }
}
