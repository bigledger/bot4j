package ai.nitro.bot4j.middle.receive.session;

import ai.nitro.bot4j.middle.domain.Session;
import ai.nitro.bot4j.middle.domain.receive.ReceiveMessage;

public interface SessionManager {

	Session getSession(ReceiveMessage receiveMessage);
}
