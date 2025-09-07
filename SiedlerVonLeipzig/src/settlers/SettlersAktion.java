package settlers;

public enum SettlersAktion {
	GO_PICKUP_LEFT,	//geht zum angegebenen Punkt und tut das Item an der Position in die linke Hand
	GO_PICKUP_RIGHT,//tut das Item in die rechte Hand (achtung Beruf des Siedlers kann sich ändern)
	GO_BRING_LEFT,	//Bringt Item in der Linken Hand zum Punkt
	GO_BRING_RIGHT,	//Bringt Item aus der rechten Hand (Achtung Beruf geht verloren)
	GO_WORK,		//Geht zum Punkt und führt speziefische Arbeit durch
	GO_FREE,		//geht zum Punkt und tut nichts
	GO_WAIT,		//geht zum Punkt und wartet
	GO_MAKE,		//macht altes Item zu neuem Item
}
