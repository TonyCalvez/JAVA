package enstabretagne.monitor;

public class Monitor3DMessages {
	/** The Constant LoggerSettingsDirNotFound. */
	public static final String Monitor3DSettingsDirNotFound = "R�pertoire de configuration du logger non trouv�";
	
	/** The Constant LoggerSettingsFileNotFound. */
	public static final String Monitor3DSettingsFileNotFound = "Fichier de configuration du logger non trouv�";
	/** The Constant LoggerParsingFailed. */
	public static final String LoggerParsingFailed = "Erreur de parsing du JSON";

	public static final String typeEntiteNonTrouve = "Le type '%s' n'a pas �t� trouv�";

	public static final String representation3DNonTrouve = "La repr�sentation 3D '%s' n'a pas �t� trouv�e";

	public static final String typeEntitePasSimEntity = "Le type '%s' n'est pas une ISimEntity";

	public static final String type3DPasDrawAction = "Le type '%s' n'est pas une IDrawAction.";
	public static final String type3DPasConstructeurAvec3DSettings = "Le type '%s' n'a pas de constructeur public possedant un seul argument de type ObjTo3DMappingSettings.";

	public static final String PasContrat = "Le type '%s' n'a pas d'annotation de type Contrat3D";

    public static final String Obj3DPasBonContrat3D = "Le type SimEntity '%s' n'implemente pas le contrat 3D '%s'";

}
