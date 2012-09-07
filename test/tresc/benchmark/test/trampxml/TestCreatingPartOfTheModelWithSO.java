package tresc.benchmark.test.trampxml;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.vagabond.mapping.model.MapScenarioHolder;
import org.vagabond.mapping.model.ModelLoader;
import org.vagabond.util.LoggerUtil;
import org.vagabond.util.PropertyWrapper;

import tresc.benchmark.Configuration;
import tresc.benchmark.Constants.ParameterName;
import tresc.benchmark.Constants.ScenarioName;
import tresc.benchmark.STBenchmark;


public class TestCreatingPartOfTheModelWithSO {

static Logger log = Logger.getLogger(TestLoadToDBWithData.class);
	
	private STBenchmark b = new STBenchmark();
	private Configuration conf;
	private static final String OUT_DIR = "./testout";
	
//	private static final ScenarioName[] scens = ScenarioName.values();
	private static final ScenarioName[] scens = new ScenarioName[] {
			ScenarioName.ADDATTRIBUTE,
			ScenarioName.ADDDELATTRIBUTE,
			ScenarioName.COPY,
			ScenarioName.DELATTRIBUTE,
			ScenarioName.FUSION,
			ScenarioName.HORIZPARTITION,
			ScenarioName.MERGEADD,
			ScenarioName.MERGING,
			ScenarioName.SELFJOINS,
			ScenarioName.SURROGATEKEY,
			ScenarioName.VALUEGEN,
			ScenarioName.VALUEMANAGEMENT,
			ScenarioName.VERTPARTITION,
			ScenarioName.VERTPARTITIONHASA,
			ScenarioName.VERTPARTITIONISA,
			ScenarioName.VERTPARTITIONNTOM
			};
	
	@BeforeClass
	public static void setUp () {
		PropertyConfigurator.configure("testresource/log4jproperties.txt");
		File outDir = new File(OUT_DIR);
		if (!outDir.exists())
			outDir.mkdir();
	}
	
	@AfterClass
	public static void tearDown () {
		/*File outDir = new File(OUT_DIR);
		if (outDir.exists()) {
			for(File child: outDir.listFiles()) {
				child.delete();
			}
			outDir.delete();
		}*/
	}

	@Before
	public void setUpConf () throws FileNotFoundException, IOException {
		PropertyWrapper prop = new PropertyWrapper("testresource/partconf.txt");
		conf = new Configuration();
		conf.readFromProperties(prop);
		conf.setInstancePathPrefix(OUT_DIR);
		conf.setSchemaPathPrefix(OUT_DIR);
	}
	
	@Test
	public void testCopy () throws Exception {
		testSingleBasicScenario(ScenarioName.COPY);
	}
	
	@Test
	public void testFusion () throws Exception {
		testSingleBasicScenario(ScenarioName.FUSION);
	}
	
	@Test
	public void testHP () throws Exception {
		testSingleBasicScenario(ScenarioName.HORIZPARTITION);
	}
	
	@Test
	public void testMerging () throws Exception {
		testSingleBasicScenario(ScenarioName.MERGING);
	}
	
	@Test
	public void testMergeAdd () throws Exception {
		testSingleBasicScenario(ScenarioName.MERGEADD);
	}
	
	@Test
	public void testSJ () throws Exception {
		testSingleBasicScenario(ScenarioName.SELFJOINS);
	}
	
	@Test
	public void testSKey () throws Exception {
		testSingleBasicScenario(ScenarioName.SURROGATEKEY);
	}
	

	@Test
	public void testValgen () throws Exception {
		testSingleBasicScenario(ScenarioName.VALUEGEN);
	}
	
	@Test
	public void testAtomValueMan () throws Exception {
		testSingleBasicScenario(ScenarioName.VALUEMANAGEMENT);
	}
	
	@Test
	public void testVerticalPart () throws Exception {
		testSingleBasicScenario(ScenarioName.VERTPARTITION);
	}
	
	@Test
	public void testVerticalPartIsA () throws Exception {
		testSingleBasicScenario(ScenarioName.VERTPARTITIONISA);
	}
	
	@Test
	public void testVerticalPartHasA () throws Exception {
		testSingleBasicScenario(ScenarioName.VERTPARTITIONHASA);
	}
	
	@Test
	public void testVerticalPartNtoM () throws Exception {
		testSingleBasicScenario(ScenarioName.VERTPARTITIONNTOM);
	}
	
	@Test
	public void testAddAttr () throws Exception {
		conf.setParam(ParameterName.NumOfNewAttributes, 3);
		conf.setParam(ParameterName.SkolemKind, 0);
		testSingleBasicScenario(ScenarioName.ADDATTRIBUTE);
		conf.setParam(ParameterName.SkolemKind, 1);
		testSingleBasicScenario(ScenarioName.ADDATTRIBUTE);
		conf.setParam(ParameterName.SkolemKind, 2);
		testSingleBasicScenario(ScenarioName.ADDATTRIBUTE);
	}
	
	
	public void testAddDelAttr () throws Exception {
		conf.setParam(ParameterName.NumOfNewAttributes, 3);
		conf.setParam(ParameterName.SkolemKind, 0);
		testSingleBasicScenario(ScenarioName.ADDDELATTRIBUTE);
		conf.setParam(ParameterName.SkolemKind, 1);
		testSingleBasicScenario(ScenarioName.ADDDELATTRIBUTE);
		conf.setParam(ParameterName.SkolemKind, 2);
		testSingleBasicScenario(ScenarioName.ADDDELATTRIBUTE);
	}
	
	@Test
	public void testDelAttr () throws Exception {
		testSingleBasicScenario(ScenarioName.DELATTRIBUTE);
	}
	
	@Test
	public void testAllBasicScenarios () throws Exception {
		//for(ScenarioName n: Constants.ScenarioName.values())
		for(ScenarioName n: scens)
			testSingleBasicScenario(n);
	}
	
	private void testSingleBasicScenario (ScenarioName n) throws Exception {
		log.info(n);
		conf.setScenarioRepetitions(n, 10);
		b.runConfig(conf);
		testLoad(n, false, false);
		conf.setScenarioRepetitions(n, 0);
	}

	
	
	private void testLoad(ScenarioName n, boolean toDB, boolean withData) throws Exception {
		try {
			MapScenarioHolder doc = ModelLoader.getInstance().load(new File(OUT_DIR,"test.xml"));
			log.debug(doc.getScenario().toString());
		}
		catch (Exception e) {
			log.error(n + "\n\n" + loadToString());
			LoggerUtil.logException(e, log);
			throw e;	
		}
	}
	
	private String loadToString () throws IOException {
		StringBuffer result = new StringBuffer();
		BufferedReader in = new BufferedReader(new FileReader(new File(OUT_DIR, "test.xml")));
		
		while(in.ready()) {
			result.append(in.readLine() + "\n");
		}
		
		in.close();
		
		return result.toString();
	}
	
}
