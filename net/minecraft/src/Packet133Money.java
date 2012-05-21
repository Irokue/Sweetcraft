package net.minecraft.src;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;

public class Packet133Money extends Packet
{
   public Packet133Money()
   {
      
   }
   
   public Packet133Money(EntityPlayer player)
   {
      playerId = player.entityId;
      
      this.money = player.money;
   }
   
   public void readPacketData(DataInputStream datainputstream)
         throws IOException
   {
      playerId = datainputstream.readInt();
      money = datainputstream.readDouble();
      money = Math.round(money*100.0)/100.0;
   }

   public void writePacketData(DataOutputStream dataoutputstream)
         throws IOException {
      dataoutputstream.writeInt(playerId);
      dataoutputstream.writeDouble(money);
   }

   public void processPacket(NetHandler nethandler) {
      nethandler.handleMoney(this);
   }

   public int getPacketSize() {
      return (int) (12 * this.money);
   }
   
   public int playerId;
   public double money;
}