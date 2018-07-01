package model;

public class Shelter {


        private Long idshelter;

        private Long nrpets;

        private Float capital;

        private String about;


        public Shelter(){}

        public Shelter(Long nrpets, Float capital, String about) {
            this.nrpets = nrpets;
            this.capital = capital;
            this.about = about;
        }

        public Long getIdshelter() {
            return idshelter;
        }

        public void setIdshelter(Long idshelter) {
            this.idshelter = idshelter;
        }

        public Long getNrpets() {
            return nrpets;
        }

        public void setNrpets(Long nrpets) {
            this.nrpets = nrpets;
        }

        public Float getCapital() {
            return capital;
        }

        public void setCapital(Float capital) {
            this.capital = capital;
        }

        public String getAbout() {
            return about;
        }

        public void setAbout(String about) {
            this.about = about;
        }

        @Override
        public String toString() {
            return "Shelter{" +
                    "idshelter=" + idshelter +
                    ", nrpets=" + nrpets +
                    ", capital=" + capital +
                    ", about='" + about + '\'' +
                    '}';
        }


}
