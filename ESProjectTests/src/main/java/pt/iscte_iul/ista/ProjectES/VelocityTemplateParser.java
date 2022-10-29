package pt.iscte_iul.ista.ProjectES;

import java.io.StringWriter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.velocity.Template;
import org.apache.velocity.VelocityContext;
import org.apache.velocity.app.VelocityEngine;

public class VelocityTemplateParser {
	public static String generateHTML() throws Exception {

		// initialize velocity engine
		VelocityEngine ve = new VelocityEngine();
		ve.init();

		List<Map<String, String>> list = new ArrayList<Map<String, String>>();
		Map<String, String> map = new HashMap<String, String>();

		map.put("name", "Cow");
		map.put("price", "$100.00");
		list.add(map);

		map = new HashMap<String, String>();
		map.put("name", "Eagle");
		map.put("price", "$59.99");
		list.add(map);

		map = new HashMap<String, String>();
		map.put("name", "Shark");
		map.put("price", "$3.99");
		list.add(map);

		// add that list to a VelocityContext
		VelocityContext context = new VelocityContext();
		context.put("petList", list);

		// get the Template
		Template t = ve.getTemplate("resources/email_html.vm");

		// render the template into a Writer, here a StringWriter
		StringWriter writer = new StringWriter();
		t.merge(context, writer);
		return writer.toString();
	}
}
