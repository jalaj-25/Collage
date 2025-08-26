public class q9 {
    static class SampleObject {
        private int id;

        public SampleObject(int id) {
            this.id = id;
            System.out.println("Object created with ID: " + id);
        }

        @Override
        protected void finalize() throws Throwable {
            System.out.println("Object with ID " + id + " is being garbage collected");
        }
    }

    public static void main(String[] args) {
        Runtime runtime = Runtime.getRuntime();

        System.out.println("Memory before object creation: " + (runtime.totalMemory() - runtime.freeMemory()) + " bytes");

        SampleObject[] objects = new SampleObject[5];
        for (int i = 0; i < objects.length; i++) {
            objects[i] = new SampleObject(i);
        }

        System.out.println("Memory after object creation: " + (runtime.totalMemory() - runtime.freeMemory()) + " bytes");

        for (int i = 0; i < objects.length; i++) {
            objects[i] = null;
        }

        System.gc();

        System.out.println("Memory after garbage collection: " + (runtime.totalMemory() - runtime.freeMemory()) + " bytes");

        try {
            Thread.sleep(1000); // Giving time for GC
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
