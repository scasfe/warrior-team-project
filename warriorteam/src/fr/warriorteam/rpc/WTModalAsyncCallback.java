package fr.warriorteam.rpc;

import com.google.gwt.core.client.GWT;

import fr.warriorteam.client.pane.WTModalWaitPane;

public abstract class WTModalAsyncCallback<T> extends WTAsyncCallback<T> {

	protected WTModalAsyncCallback() {
		showModal();
	}

	private void showModal() {
		WTModalWaitPane.getInstance().setVisible(true);
		WTModalWaitPane.getInstance().show();

	}

	private void hideModal() {
		WTModalWaitPane.getInstance().hide();
	}

	public abstract void handleResult(T result);

	protected void handleOnFailure(Throwable t) {
		GWT.log(WTModalAsyncCallback.class + "onFailure");
		WTModalWaitPane.getInstance().forceHide();
		super.handleOnFailure(t);
	}

	protected void handleOnSuccess(T result) {
		GWT.log(WTModalAsyncCallback.class + "onSuccess");
		hideModal();
		super.handleOnSuccess(result);
	}
}
