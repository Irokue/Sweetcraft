package net.minecraft.src;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;

public class Packet136Quest extends Packet{

	public String questsTitles;
	public String questsDesc;
    public String questsObjectives;
    public String questsRewards;
    public int playerId;
	
    public Packet136Quest(){}
    
    public Packet136Quest(EntityPlayer player){
    	this.playerId = player.entityId;
    	this.questsTitles = player.questsTitles;
    	this.questsDesc = player.questsDesc;
    	this.questsObjectives = player.questsObjectives;
    	this.questsRewards = player.questsRewards;
    }
    
	@Override
	public void readPacketData(DataInputStream datainputstream)
			throws IOException {
		playerId = datainputstream.readInt();
		questsTitles = readString(datainputstream, 32767);
		questsDesc = readString(datainputstream, 32767);
		questsObjectives = readString(datainputstream, 32767);
		questsRewards = readString(datainputstream, 32767);
	}

	@Override
	public void writePacketData(DataOutputStream dataoutputstream)
			throws IOException {
		dataoutputstream.writeInt(playerId);
		writeString(questsTitles, dataoutputstream);
		writeString(questsDesc, dataoutputstream);
		writeString(questsObjectives, dataoutputstream);
		writeString(questsRewards, dataoutputstream);
	}

	@Override
	public void processPacket(NetHandler nethandler) {
		nethandler.handleQuests(this);
	}

	@Override
	public int getPacketSize() {
		return 300;
	}
	
	

}
