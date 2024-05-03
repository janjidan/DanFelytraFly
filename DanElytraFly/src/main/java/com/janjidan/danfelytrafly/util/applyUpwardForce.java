package com.janjidan.danfelytrafly.util;

import org.bukkit.entity.Player;
import org.bukkit.util.Vector;

public class applyUpwardForce {
    public static void applyUpwardForceToPlayer(Player player, double force, double gravity) {
        // 获取玩家当前的朝向
        float yaw = player.getLocation().getYaw();

        // 将角度转换为弧度
        double yawRadians = Math.toRadians(yaw);

        // 计算水平方向的力分量
        double x = -Math.sin(yawRadians); // Minecraft的正Z方向指向南，所以这里使用负号
        double z = Math.cos(yawRadians);

        // 根据gravity调整y分量，以改变弹射角度
        // 为了使角度变化更加明显，我们可以使用gravity参数直接作为垂直分量的倍数
        // 这里不再简化为y = 1.0，而是根据gravity来动态调整
        double y = gravity;

        // 创建向量并规范化，然后乘以力的大小
        Vector direction = new Vector(x, y, z).normalize().multiply(force);

        // 给玩家施加这个力
        player.setVelocity(player.getVelocity().add(direction));
    }
}
