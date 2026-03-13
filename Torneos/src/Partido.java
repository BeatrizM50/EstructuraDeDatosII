public class Partido {
        String equipo1;
        String equipo2;
        String ganador;
        String ronda;

        public Partido(String equipo1, String equipo2, String ronda) {
            this.equipo1 = equipo1;
            this.equipo2 = equipo2;
            this.ganador = null;
            this.ronda = ronda;
        }
        public Partido(String ronda) {
            this.equipo1 = null;
            this.equipo2 = null;
            this.ganador = null;
            this.ronda = ronda;
        }

        public String getEquipo1() {
            return equipo1;
        }

        public void setEquipo1(String equipo1) {
            this.equipo1 = equipo1;
        }

        public String getEquipo2() {
            return equipo2;
        }

        public void setEquipo2(String equipo2) {
            this.equipo2 = equipo2;
        }

        public String getGanador() {
            return ganador;
        }

        public void setGanador(String ganador) {
            this.ganador = ganador;
        }

        public String getRonda() {
            return ronda;
        }

        public void setRonda(String ronda) {
            this.ronda = ronda;
        }
    }


