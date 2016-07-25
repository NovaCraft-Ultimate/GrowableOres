package net.spideynn.bukkit.growableores;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.scheduler.BukkitRunnable;

import java.io.Serializable;

class GrowTimer implements Runnable, Serializable {

	private static final long serialVersionUID = 1406104898463223228L;
	long timeToGrow;
	OrePlant plant;
	long timeElapsed;
	boolean running = true;
	
	GrowTimer(float timeToGrow, OrePlant plant, long timeElapsed)
	{
		this.timeToGrow = (long) (timeToGrow*1f);
		this.plant = plant;
		this.timeElapsed = timeElapsed;
	}
	
	@Override
	public void run() {
		//GrowableOres.instance.log.info("starting timer");
		while(running)
		{
			//GrowableOres.instance.log.info("pre-sleep");
			try {
				Thread.sleep(1000);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			timeElapsed++;
			plant.timeElapsed = timeElapsed;
			final GrowTimer instance = this;
			/*for (Player player : Bukkit.getOnlinePlayers()) {
				if (player.getName().equals("Spideynn")) {
                    player.sendMessage("loc x: " + plant.base.x + " y: " + plant.base.y + " z: " + plant.base.z + " elapsedTime = " + timeElapsed + " timeToGrow = " + timeToGrow);
                }
			}*/
			//GrowableOres.instance.log.info("loc x: " + plant.base.x + " y: " + plant.base.y + " z: " + plant.base.z + " elapsedTime = " + timeElapsed + " timeToGrow = " + timeToGrow);
			if(timeElapsed >= timeToGrow)
                new BukkitRunnable() {
                    @Override
                    public void run() {
                        plant.grow(instance);
                    }
                }.runTask(GrowableOres.instance);
		}
	}

}
