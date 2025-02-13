package dev.latvian.mods.kubejs.event;

import dev.latvian.mods.kubejs.script.ScriptType;

/**
 * @author LatvianModder
 */
public class EventJS {
	private boolean cancelled = false;

	public boolean canCancel() {
		return false;
	}

	public final void cancel() {
		cancelled = true;
	}

	public final boolean isCancelled() {
		return cancelled;
	}

	protected void afterPosted(boolean result) {
	}

	public final boolean post(ScriptType t, String id) {
		if (t != ScriptType.STARTUP && post(ScriptType.STARTUP, id) && canCancel()) {
			return true;
		}

		var e = t.manager.get().events;
		var b = e.postToHandlers(id, e.handlers(id), this);
		afterPosted(b);
		return b;
	}

	public final boolean post(ScriptType t, String id, String sub) {
		var id1 = id + '.' + sub;

		if (t != ScriptType.STARTUP) {
			var e = ScriptType.STARTUP.manager.get().events;
			if ((e.postToHandlers(id1, e.handlers(id1), this) || e.postToHandlers(id, e.handlers(id), this)) && canCancel()) {
				afterPosted(true);
				return true;
			}
		}

		var e = t.manager.get().events;
		var b = e.postToHandlers(id1, e.handlers(id1), this) || e.postToHandlers(id, e.handlers(id), this);
		afterPosted(b);
		return b;
	}
}