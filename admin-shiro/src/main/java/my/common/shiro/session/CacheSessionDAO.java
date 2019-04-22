package my.common.shiro.session;

import java.io.Serializable;

import org.apache.shiro.session.Session;
import org.apache.shiro.session.mgt.eis.CachingSessionDAO;

public class CacheSessionDAO extends CachingSessionDAO implements SessionDAO {

	@Override
	protected void doUpdate(Session session) {
		// TODO Auto-generated method stub

	}

	@Override
	protected void doDelete(Session session) {
		if ((session == null) || (session.getId() == null)) {
			return;
		}

	}

	@Override
	protected Serializable doCreate(Session session) {
		return null;
	}

	@Override
	protected Session doReadSession(Serializable sessionId) {
		// TODO Auto-generated method stub
		return null;
	}

}
