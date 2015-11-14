package com.example.kushagrjolly.weather;

/**
 * Created by ayushb on 15/11/15.
 */
class WekaClassifier {

    static double FOG = 0;
    static double PARTLY_CLOUDY_NIGHT = 1;
    static double WIND = 3;
    static double CLEAR_DAY = 2;
    static double PARTLY_CLOUD_DAY = 4;

    public static double classify(Object[] i) {

        double p = Double.NaN;
        p = WekaClassifier.N5a0922a68(i);
        return p;
    }

    static double N5a0922a68(Object[] i) {
        double p = Double.NaN;
        if (i[9] == null) {
            p = 0;
        } else if (((Double) i[9]).doubleValue() <= 2.05) {
            p = WekaClassifier.N70dc064869(i);
        } else if (((Double) i[9]).doubleValue() > 2.05) {
            p = WekaClassifier.N282161ee74(i);
        }
        return p;
    }

    static double N70dc064869(Object[] i) {
        double p = Double.NaN;
        if (i[11] == null) {
            p = 0;
        } else if (((Double) i[11]).doubleValue() <= 8.01) {
            p = 0;
        } else if (((Double) i[11]).doubleValue() > 8.01) {
            p = WekaClassifier.N7d209ada70(i);
        }
        return p;
    }

    static double N7d209ada70(Object[] i) {
        double p = Double.NaN;
        if (i[1] == null) {
            p = 0;
        } else if (((Double) i[1]).doubleValue() <= 44.67) {
            p = WekaClassifier.N67969e6371(i);
        } else if (((Double) i[1]).doubleValue() > 44.67) {
            p = WekaClassifier.N5178aab672(i);
        }
        return p;
    }

    static double N67969e6371(Object[] i) {
        double p = Double.NaN;
        if (i[1] == null) {
            p = 0;
        } else if (((Double) i[1]).doubleValue() <= 43.53) {
            p = 0;
        } else if (((Double) i[1]).doubleValue() > 43.53) {
            p = 3;
        }
        return p;
    }

    static double N5178aab672(Object[] i) {
        double p = Double.NaN;
        if (i[10] == null) {
            p = 0;
        } else if (((Double) i[10]).doubleValue() <= 93.0) {
            p = WekaClassifier.N490fbc8373(i);
        } else if (((Double) i[10]).doubleValue() > 93.0) {
            p = 0;
        }
        return p;
    }

    static double N490fbc8373(Object[] i) {
        double p = Double.NaN;
        if (i[10] == null) {
            p = 0;
        } else if (((Double) i[10]).doubleValue() <= 91.0) {
            p = 0;
        } else if (((Double) i[10]).doubleValue() > 91.0) {
            p = 4;
        }
        return p;
    }

    static double N282161ee74(Object[] i) {
        double p = Double.NaN;
        if (i[0] == null) {
            p = 0;
        } else if (((Double) i[0]).doubleValue() <= 0.22) {
            p = WekaClassifier.N27a464d775(i);
        } else if (((Double) i[0]).doubleValue() > 0.22) {
            p = WekaClassifier.N6a6cc3d191(i);
        }
        return p;
    }

    static double N27a464d775(Object[] i) {
        double p = Double.NaN;
        if (i[2] == null) {
            p = 0;
        } else if (((Double) i[2]).doubleValue() <= 0.53) {
            p = WekaClassifier.N19d8739676(i);
        } else if (((Double) i[2]).doubleValue() > 0.53) {
            p = WekaClassifier.N977ccc389(i);
        }
        return p;
    }

    static double N19d8739676(Object[] i) {
        double p = Double.NaN;
        if (i[2] == null) {
            p = 0;
        } else if (((Double) i[2]).doubleValue() <= 0.49) {
            p = WekaClassifier.N518c8a1b77(i);
        } else if (((Double) i[2]).doubleValue() > 0.49) {
            p = WekaClassifier.N1b8ca1c084(i);
        }
        return p;
    }

    static double N518c8a1b77(Object[] i) {
        double p = Double.NaN;
        if (i[11] == null) {
            p = 0;
        } else if (((Double) i[11]).doubleValue() <= 7.09) {
            p = 0;
        } else if (((Double) i[11]).doubleValue() > 7.09) {
            p = WekaClassifier.N61bf1c7078(i);
        }
        return p;
    }

    static double N61bf1c7078(Object[] i) {
        double p = Double.NaN;
        if (i[11] == null) {
            p = 0;
        } else if (((Double) i[11]).doubleValue() <= 7.33) {
            p = WekaClassifier.N49e42f3279(i);
        } else if (((Double) i[11]).doubleValue() > 7.33) {
            p = WekaClassifier.N23d6a18480(i);
        }
        return p;
    }

    static double N49e42f3279(Object[] i) {
        double p = Double.NaN;
        if (i[0] == null) {
            p = 0;
        } else if (((Double) i[0]).doubleValue() <= 0.05) {
            p = 0;
        } else if (((Double) i[0]).doubleValue() > 0.05) {
            p = 1;
        }
        return p;
    }

    static double N23d6a18480(Object[] i) {
        double p = Double.NaN;
        if (i[10] == null) {
            p = 0;
        } else if (((Double) i[10]).doubleValue() <= 292.0) {
            p = WekaClassifier.N6005f37d81(i);
        } else if (((Double) i[10]).doubleValue() > 292.0) {
            p = 0;
        }
        return p;
    }

    static double N6005f37d81(Object[] i) {
        double p = Double.NaN;
        if (i[1] == null) {
            p = 2;
        } else if (((Double) i[1]).doubleValue() <= 49.03) {
            p = 2;
        } else if (((Double) i[1]).doubleValue() > 49.03) {
            p = WekaClassifier.N26b4c4cb82(i);
        }
        return p;
    }

    static double N26b4c4cb82(Object[] i) {
        double p = Double.NaN;
        if (i[8] == null) {
            p = 0;
        } else if (((Double) i[8]).doubleValue() <= 1001.06) {
            p = 0;
        } else if (((Double) i[8]).doubleValue() > 1001.06) {
            p = WekaClassifier.N25e3ff4783(i);
        }
        return p;
    }

    static double N25e3ff4783(Object[] i) {
        double p = Double.NaN;
        if (i[8] == null) {
            p = 2;
        } else if (((Double) i[8]).doubleValue() <= 1007.05) {
            p = 2;
        } else if (((Double) i[8]).doubleValue() > 1007.05) {
            p = 0;
        }
        return p;
    }

    static double N1b8ca1c084(Object[] i) {
        double p = Double.NaN;
        if (i[11] == null) {
            p = 2;
        } else if (((Double) i[11]).doubleValue() <= 7.64) {
            p = WekaClassifier.N2dcf526185(i);
        } else if (((Double) i[11]).doubleValue() > 7.64) {
            p = WekaClassifier.N60a3a4688(i);
        }
        return p;
    }

    static double N2dcf526185(Object[] i) {
        double p = Double.NaN;
        if (i[9] == null) {
            p = 0;
        } else if (((Double) i[9]).doubleValue() <= 2.37) {
            p = WekaClassifier.N62975c686(i);
        } else if (((Double) i[9]).doubleValue() > 2.37) {
            p = 2;
        }
        return p;
    }

    static double N62975c686(Object[] i) {
        double p = Double.NaN;
        if (i[2] == null) {
            p = 0;
        } else if (((Double) i[2]).doubleValue() <= 0.51) {
            p = WekaClassifier.Nbf31d5387(i);
        } else if (((Double) i[2]).doubleValue() > 0.51) {
            p = 0;
        }
        return p;
    }

    static double Nbf31d5387(Object[] i) {
        double p = Double.NaN;
        if (i[10] == null) {
            p = 0;
        } else if (((Double) i[10]).doubleValue() <= 294.0) {
            p = 0;
        } else if (((Double) i[10]).doubleValue() > 294.0) {
            p = 2;
        }
        return p;
    }

    static double N60a3a4688(Object[] i) {
        double p = Double.NaN;
        if (i[2] == null) {
            p = 0;
        } else if (((Double) i[2]).doubleValue() <= 0.52) {
            p = 0;
        } else if (((Double) i[2]).doubleValue() > 0.52) {
            p = 1;
        }
        return p;
    }

    static double N977ccc389(Object[] i) {
        double p = Double.NaN;
        if (i[9] == null) {
            p = 0;
        } else if (((Double) i[9]).doubleValue() <= 2.59) {
            p = 0;
        } else if (((Double) i[9]).doubleValue() > 2.59) {
            p = WekaClassifier.N18241b1090(i);
        }
        return p;
    }

    static double N18241b1090(Object[] i) {
        double p = Double.NaN;
        if (i[1] == null) {
            p = 4;
        } else if (((Double) i[1]).doubleValue() <= 69.19) {
            p = 4;
        } else if (((Double) i[1]).doubleValue() > 69.19) {
            p = 0;
        }
        return p;
    }

    static double N6a6cc3d191(Object[] i) {
        double p = Double.NaN;
        if (i[9] == null) {
            p = 0;
        } else if (((Double) i[9]).doubleValue() <= 2.3) {
            p = WekaClassifier.Naac04ed92(i);
        } else if (((Double) i[9]).doubleValue() > 2.3) {
            p = WekaClassifier.N5e9c3ce7104(i);
        }
        return p;
    }

    static double Naac04ed92(Object[] i) {
        double p = Double.NaN;
        if (i[11] == null) {
            p = 0;
        } else if (((Double) i[11]).doubleValue() <= 8.2) {
            p = WekaClassifier.N2377691493(i);
        } else if (((Double) i[11]).doubleValue() > 8.2) {
            p = WekaClassifier.N3018e40b100(i);
        }
        return p;
    }

    static double N2377691493(Object[] i) {
        double p = Double.NaN;
        if (i[10] == null) {
            p = 0;
        } else if (((Double) i[10]).doubleValue() <= 52.0) {
            p = WekaClassifier.N7493b23c94(i);
        } else if (((Double) i[10]).doubleValue() > 52.0) {
            p = WekaClassifier.Nc9abcef95(i);
        }
        return p;
    }

    static double N7493b23c94(Object[] i) {
        double p = Double.NaN;
        if (i[10] == null) {
            p = 0;
        } else if (((Double) i[10]).doubleValue() <= 48.0) {
            p = 0;
        } else if (((Double) i[10]).doubleValue() > 48.0) {
            p = 1;
        }
        return p;
    }

    static double Nc9abcef95(Object[] i) {
        double p = Double.NaN;
        if (i[2] == null) {
            p = 0;
        } else if (((Double) i[2]).doubleValue() <= 0.7) {
            p = WekaClassifier.N2e9f56096(i);
        } else if (((Double) i[2]).doubleValue() > 0.7) {
            p = WekaClassifier.N5e79de8998(i);
        }
        return p;
    }

    static double N2e9f56096(Object[] i) {
        double p = Double.NaN;
        if (i[1] == null) {
            p = 0;
        } else if (((Double) i[1]).doubleValue() <= 67.68) {
            p = WekaClassifier.N4dec731e97(i);
        } else if (((Double) i[1]).doubleValue() > 67.68) {
            p = 0;
        }
        return p;
    }

    static double N4dec731e97(Object[] i) {
        double p = Double.NaN;
        if (i[2] == null) {
            p = 4;
        } else if (((Double) i[2]).doubleValue() <= 0.25) {
            p = 4;
        } else if (((Double) i[2]).doubleValue() > 0.25) {
            p = 0;
        }
        return p;
    }

    static double N5e79de8998(Object[] i) {
        double p = Double.NaN;
        if (i[10] == null) {
            p = 0;
        } else if (((Double) i[10]).doubleValue() <= 87.0) {
            p = WekaClassifier.N130124d499(i);
        } else if (((Double) i[10]).doubleValue() > 87.0) {
            p = 0;
        }
        return p;
    }

    static double N130124d499(Object[] i) {
        double p = Double.NaN;
        if (i[2] == null) {
            p = 0;
        } else if (((Double) i[2]).doubleValue() <= 0.76) {
            p = 0;
        } else if (((Double) i[2]).doubleValue() > 0.76) {
            p = 4;
        }
        return p;
    }

    static double N3018e40b100(Object[] i) {
        double p = Double.NaN;
        if (i[10] == null) {
            p = 0;
        } else if (((Double) i[10]).doubleValue() <= 101.0) {
            p = 0;
        } else if (((Double) i[10]).doubleValue() > 101.0) {
            p = WekaClassifier.N3223a6d8101(i);
        }
        return p;
    }

    static double N3223a6d8101(Object[] i) {
        double p = Double.NaN;
        if (i[10] == null) {
            p = 4;
        } else if (((Double) i[10]).doubleValue() <= 269.0) {
            p = WekaClassifier.N429ec89f102(i);
        } else if (((Double) i[10]).doubleValue() > 269.0) {
            p = 0;
        }
        return p;
    }

    static double N429ec89f102(Object[] i) {
        double p = Double.NaN;
        if (i[2] == null) {
            p = 4;
        } else if (((Double) i[2]).doubleValue() <= 0.68) {
            p = WekaClassifier.N468560e4103(i);
        } else if (((Double) i[2]).doubleValue() > 0.68) {
            p = 2;
        }
        return p;
    }

    static double N468560e4103(Object[] i) {
        double p = Double.NaN;
        if (i[10] == null) {
            p = 4;
        } else if (((Double) i[10]).doubleValue() <= 179.0) {
            p = 4;
        } else if (((Double) i[10]).doubleValue() > 179.0) {
            p = 2;
        }
        return p;
    }

    static double N5e9c3ce7104(Object[] i) {
        double p = Double.NaN;
        if (i[1] == null) {
            p = 0;
        } else if (((Double) i[1]).doubleValue() <= 58.18) {
            p = WekaClassifier.N63629237105(i);
        } else if (((Double) i[1]).doubleValue() > 58.18) {
            p = WekaClassifier.N5c6d8bda106(i);
        }
        return p;
    }

    static double N63629237105(Object[] i) {
        double p = Double.NaN;
        if (i[0] == null) {
            p = 0;
        } else if (((Double) i[0]).doubleValue() <= 0.44) {
            p = 0;
        } else if (((Double) i[0]).doubleValue() > 0.44) {
            p = 2;
        }
        return p;
    }

    static double N5c6d8bda106(Object[] i) {
        double p = Double.NaN;
        if (i[9] == null) {
            p = 4;
        } else if (((Double) i[9]).doubleValue() <= 3.1) {
            p = WekaClassifier.N1bf8c49e107(i);
        } else if (((Double) i[9]).doubleValue() > 3.1) {
            p = 0;
        }
        return p;
    }

    static double N1bf8c49e107(Object[] i) {
        double p = Double.NaN;
        if (i[9] == null) {
            p = 4;
        } else if (((Double) i[9]).doubleValue() <= 2.49) {
            p = WekaClassifier.N69346f6a108(i);
        } else if (((Double) i[9]).doubleValue() > 2.49) {
            p = 4;
        }
        return p;
    }

    static double N69346f6a108(Object[] i) {
        double p = Double.NaN;
        if (i[0] == null) {
            p = 0;
        } else if (((Double) i[0]).doubleValue() <= 0.71) {
            p = WekaClassifier.N717ece1b109(i);
        } else if (((Double) i[0]).doubleValue() > 0.71) {
            p = 4;
        }
        return p;
    }

    static double N717ece1b109(Object[] i) {
        double p = Double.NaN;
        if (i[10] == null) {
            p = 4;
        } else if (((Double) i[10]).doubleValue() <= 263.0) {
            p = 4;
        } else if (((Double) i[10]).doubleValue() > 263.0) {
            p = WekaClassifier.N3c0d88d3110(i);
        }
        return p;
    }

    static double N3c0d88d3110(Object[] i) {
        double p = Double.NaN;
        if (i[1] == null) {
            p = 0;
        } else if (((Double) i[1]).doubleValue() <= 75.84) {
            p = WekaClassifier.N1c91f372111(i);
        } else if (((Double) i[1]).doubleValue() > 75.84) {
            p = 4;
        }
        return p;
    }

    static double N1c91f372111(Object[] i) {
        double p = Double.NaN;
        if (i[11] == null) {
            p = 0;
        } else if (((Double) i[11]).doubleValue() <= 6.15) {
            p = 0;
        } else if (((Double) i[11]).doubleValue() > 6.15) {
            p = 2;
        }
        return p;
    }

    public String wekaClassifier(Forecast forecast) {
        Object[] data = new Object[12];
        data[0] = forecast.getCloudClover();
        data[1] = forecast.getDewPoint();
        data[2] = forecast.getHumidity();
        data[3] = forecast.getIcon();
        data[4] = forecast.getOzone();
        data[5] = forecast.getPrecipIntensity();
        data[6] = forecast.getPrecipProbability();
        data[7] = forecast.getPrecipType();
        data[8] = forecast.getPressure();
        data[9] = forecast.getVisibility();
        data[10] = forecast.getWindBearing();
        data[11] = forecast.getWindSpeed();

        double classification = classify(data);
        if (classification == FOG)
            return "fog";
        if (classification == PARTLY_CLOUD_DAY)
            return "partly-cloudy";
        if (classification == PARTLY_CLOUDY_NIGHT)
            return "partly-cloudy";
        if (classification == CLEAR_DAY)
            return "clear-day";
        if (classification == WIND)
            return "wind";

        return null;
    }
}