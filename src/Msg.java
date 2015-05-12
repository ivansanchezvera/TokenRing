import java.util.StringTokenizer;


public class Msg {

	int srcId, destId;
	String tag;
	String msgBuf;
	
	public Msg(int source, int destination, String messageType, String buf) {
		// TODO Auto-generated constructor stub
		this.srcId = source;
		this.destId = destination;
		this.tag = messageType;
		this.msgBuf = buf;
		
	}

	public int getSrcId() {
		return srcId;
	}

	public void setSrcId(int srcId) {
		this.srcId = srcId;
	}

	public int getDestId() {
		return destId;
	}

	public void setDestId(int destId) {
		this.destId = destId;
	}

	public String getTag() {
		return tag;
	}

	public void setTag(String tag) {
		this.tag = tag;
	}

	public String getMsgBuf() {
		return msgBuf;
	}

	public void setMsgBuf(String msgBuf) {
		this.msgBuf = msgBuf;
	}

	public static Msg parseMsg(StringTokenizer st){
		int srcId = Integer.parseInt(st.nextToken());
		int destId = Integer.parseInt(st.nextToken());
		String tag = st.nextToken();
		String buf = st.nextToken();
		return new Msg(srcId,destId,tag,buf);
	}

	@Override
	public String toString() {
		String s = String.valueOf(srcId) + " " + String.valueOf(destId) + " "
				+ tag + " " + msgBuf + "#";
		return s;
	}
	
	
	
}
