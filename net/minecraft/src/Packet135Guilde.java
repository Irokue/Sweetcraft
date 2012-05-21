package net.minecraft.src;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;

public class Packet135Guilde extends Packet {

	public String guilde;
	public String guildeRank;
	public int playerId;
	
	public Packet135Guilde(){}
	public Packet135Guilde(EntityPlayer player){
		this.playerId = player.entityId;
		if(player.guilde.length() > 15){
			player.guilde.substring(0, 15);
		}
		this.guilde = player.guilde;
		if(player.guildeRank.length() > 15){
			player.guildeRank.substring(0, 15);
		}
		this.guildeRank = player.guildeRank;
	}
	
	@Override
	public void readPacketData(DataInputStream datainputstream)
			throws IOException {
		playerId = datainputstream.readInt();
		guilde = readString(datainputstream, 30);
		guildeRank = readString(datainputstream, 20);
	}

	@Override
	public void writePacketData(DataOutputStream dataoutputstream)
			throws IOException {
		dataoutputstream.writeInt(playerId);
		writeString(guilde, dataoutputstream);
		writeString(guildeRank, dataoutputstream);
	}

	@Override
	public void processPacket(NetHandler nethandler) {
		nethandler.handleGuilde(this);
	}

	@Override
	public int getPacketSize() {
		return 24;
	}

}
