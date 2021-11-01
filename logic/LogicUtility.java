import com.google.common.collect.AbstractIterator;
import org.bukkit.Location;
import org.bukkit.World;

/**
 * This is a implementation of listing blocks between to positions. 
 * 
 * This implementation is made for Spigot, a minecraft server library
 * 
 * @author avolgha
 */
public class LogicUtility {
    public static class Location {
        private int x, y, z;

        public Location() {
            this(0, 0, 0);
        }

        public Location(int x, int y, int z) {
            this.x = x;
            this.y = y;
            this.z = z;
        }

        public Location overwrite(int x, int y, int z) {
            this.x = x;
            this.y = y;
            this.z = z;
            return this;
        }

        public int getX() {
            return x;
        }

        public void setX(int x) {
            this.x = x;
        }

        public int getY() {
            return y;
        }

        public void setY(int y) {
            this.y = y;
        }

        public int getZ() {
            return z;
        }

        public void setZ(int z) {
            this.z = z;
        }

        public org.bukkit.Location toBukkit(World world) {
            return new org.bukkit.Location(world, this.x, this.y, this.z);
        }
    }

    public static Iterable<Location> listBlocks(final int startX, final int startY, final int startZ, int endX, int endY, int endZ) {
        final int i = endX - startX + 1;
        final int j = endY - startY + 1;

        int k = endZ - startZ + 1;

        final int l = i * j * k;

        return () -> new AbstractIterator<Location>() {
            private final Location location = new Location();

            private int h;

            protected Location computeNext() {
                if (this.h == l)
                    return endOfData();
                int offsetX = this.h % i;
                int u = this.h / i;
                int offsetY = u % j;
                int offsetZ = u / j;
                this.h++;
                return this.location.overwrite(startX + offsetX, startY + offsetY, startZ + offsetZ);
            }
        };
    }
}
