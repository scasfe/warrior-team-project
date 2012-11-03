package fr.warriorteam.rpc;

import com.google.gwt.core.client.GWT;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.rpc.AsyncCallback;

import fr.warriorteam.client.pane.WTModalWaitPane;
import fr.warriorteam.server.exception.WebFonctionnelleException;

public abstract class WTAsyncCallback<T> implements AsyncCallback<T> {

	// private final Logger logger = Logger.getLogger(WTAsyncCallback.class);

	private long time;

	protected WTAsyncCallback() {
		time = System.currentTimeMillis();
	}

	public abstract void handleResult(T result);

	public void handleFailure(Throwable t) {
		WTModalWaitPane.getInstance().forceHide();
	}

	protected void handleOnFailure(Throwable t) {
		time = System.currentTimeMillis() - time;
		GWT.log("APPEL DE SERVICE RPC : " + this.getClass()
				+ " : echec (temps total = " + time + ")");

		if (t instanceof WebFonctionnelleException) {
			Window.alert(t.getMessage());
		} else {
			Window.alert("ERREUR SERVEUR NON PREVUE"
					+ "Libelle de l'erreur dans les logs serveur : ");
		}

		handleFailure(t);
	}

	protected void handleOnSuccess(T result) {
		time = System.currentTimeMillis() - time;
		GWT.log(getClass() + " APPEL DE SERVICE RPC : " + this.getClass()
				+ " : succes (temps total = " + time + ")");
		handleResult(result);
	}

	public final void onFailure(Throwable t) {
		handleOnFailure(t);
	}

	public final void onSuccess(T result) {
		handleOnSuccess(result);
	}

}
