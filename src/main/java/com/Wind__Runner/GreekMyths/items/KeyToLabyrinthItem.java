package com.Wind__Runner.GreekMyths.items;

import com.Wind__Runner.GreekMyths.GreekMyths;
import com.ibm.icu.impl.coll.UVector32;
import net.minecraft.advancements.CriteriaTriggers;
import net.minecraft.block.*;
import net.minecraft.command.impl.SpawnPointCommand;
import net.minecraft.command.impl.TeleportCommand;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.ServerPlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.server.MinecraftServer;
import net.minecraft.server.management.DemoPlayerInteractionManager;
import net.minecraft.server.management.PlayerInteractionManager;
import net.minecraft.server.management.PlayerList;
import net.minecraft.tags.BlockTags;
import net.minecraft.util.ActionResult;
import net.minecraft.util.Hand;
import net.minecraft.util.RegistryKey;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.MathHelper;
import net.minecraft.util.math.vector.Vector3d;
import net.minecraft.world.DimensionType;
import net.minecraft.world.World;
import net.minecraft.world.gen.Heightmap;
import net.minecraft.world.server.ServerWorld;
import net.minecraftforge.common.util.ITeleporter;
import org.lwjgl.system.CallbackI;

import javax.annotation.Nullable;
import java.util.Optional;
import java.util.function.Function;

public class KeyToLabyrinthItem extends Item {
    public KeyToLabyrinthItem(Properties properties) {
        super(properties);
    }

    @Override
    public ActionResult<ItemStack> onItemRightClick(World worldIn, PlayerEntity playerIn, Hand handIn) {
        if (worldIn instanceof ServerWorld) {
            ServerWorld serverworld = (ServerWorld) worldIn;
            MinecraftServer minecraftserver = serverworld.getServer();
            ServerWorld serverworld1 = worldIn.getDimensionKey() == GreekMyths.LABYRINTH ? minecraftserver.getWorld(World.OVERWORLD) : minecraftserver.getWorld(GreekMyths.LABYRINTH);
            playerIn.changeDimension(serverworld1, new LabyrinthTeleporter(serverworld));
        }
        return super.onItemRightClick(worldIn, playerIn, handIn);

    }

    private class LabyrinthTeleporter implements ITeleporter {

        protected final ServerWorld world;

        public LabyrinthTeleporter(ServerWorld worldIn) {
            this.world = worldIn;
        }

        @Override
        public Entity placeEntity(Entity entity, ServerWorld currentWorld, ServerWorld destWorld, float yaw, Function<Boolean, Entity> repositionEntity) {
            return repositionEntity.apply(true);
        }

        @Nullable
        @Override
        public PortalInfo getPortalInfo(Entity entity, ServerWorld destWorld, Function<ServerWorld, PortalInfo> defaultPortalInfo) {
            if(destWorld.getDimensionKey() == GreekMyths.LABYRINTH) {
                double x = entity.getPosX();
                double y = 69;
                double z = entity.getPosZ();
                int addx = 0;
                int addz = 0;
                boolean badPos = true;
                while (badPos == true) {
                    if (destWorld.isAirBlock(new BlockPos(x + addx, y, z + addz)) && destWorld.isAirBlock(new BlockPos(x + addx, y + 1, z + addz))) { //&& world.getBlockState(new BlockPos(x,y-1,z)).getBlock() == Blocks.POLISHED_BLACKSTONE_BRICKS){
                        badPos = false;
                    } else {
                        if (addx < 32) {
                            addx++;
                        } else {
                            addx = 0;
                            addz++;
                        }
                    }
                }

                return new PortalInfo(new Vector3d(x + addx, y-1, z + addz), entity.getMotion(), entity.rotationYaw, entity.rotationPitch);
            }
            else {
                BlockPos spawnPoint = destWorld.getHeight(Heightmap.Type.MOTION_BLOCKING_NO_LEAVES, destWorld.getSpawnPoint());
                Vector3d spawn = (new Vector3d(spawnPoint.getX(), spawnPoint.getY(), spawnPoint.getZ()));
                if (entity instanceof ServerPlayerEntity) {
                    ServerPlayerEntity serverPlayerEntity = (ServerPlayerEntity) entity;
                    BlockPos blockpos = serverPlayerEntity.func_241140_K_();
                    float f = serverPlayerEntity.func_242109_L();
                    boolean flag = serverPlayerEntity.func_241142_M_();
                    ServerWorld serverworld = serverPlayerEntity.server.getWorld(serverPlayerEntity.func_241141_L_());
                    Optional<Vector3d> optional;
                    if (serverworld != null && blockpos != null) {
                        optional = PlayerEntity.func_242374_a(serverworld, blockpos, f, flag, true);
                    } else {
                        optional = Optional.empty();
                    }
                    if (optional.isPresent()) {
                        spawn = optional.get();
                    }
                }
                return new PortalInfo(spawn, entity.getMotion(), entity.rotationYaw, entity.rotationPitch);
            }
        }

        @Nullable
        protected PortalInfo dummyFunction(ServerWorld p_241829_1_) {
            return new PortalInfo(new Vector3d(0.0D, -1.0D, 0.0D), Vector3d.ZERO, 90.0F, 0.0F);
        }
    }
}
