package net.minecraft.src;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;

public class Packet134Job extends Packet {

	public int playerId;
	public String job;
	public int jobLevel;
	public int jobExperience;
	public int jobMaxExperience;
	
	public Packet134Job(){
		
	}
	
	public Packet134Job(EntityPlayer player){
		playerId = player.entityId;
		if(player.job.length() > 20){
			player.job = player.job.substring(0, 20);
		}
		job = player.job;
		jobLevel = player.jobLevel;
		jobExperience = player.jobExperience;
		jobMaxExperience = player.jobMaxExperience;
	}
	
	@Override
	public void readPacketData(DataInputStream datainputstream)
			throws IOException {
		playerId = datainputstream.readInt();
		job = readString(datainputstream, 20);
		jobLevel = datainputstream.readInt();
		jobExperience = datainputstream.readInt();
		jobMaxExperience = datainputstream.readInt();
	}

	@Override
	public void writePacketData(DataOutputStream dataoutputstream)
			throws IOException {
		dataoutputstream.writeInt(playerId);
		writeString(job, dataoutputstream);
		dataoutputstream.writeInt(jobLevel);
		dataoutputstream.writeInt(jobExperience);
		dataoutputstream.writeInt(jobMaxExperience);
	}

	@Override
	public void processPacket(NetHandler nethandler) {
		nethandler.handleJob(this);
	}

	@Override
	public int getPacketSize() {
		// TODO Auto-generated method stub
		return 12;
	}

}
