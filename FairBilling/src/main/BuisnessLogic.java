package main;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Deque;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class BuisnessLogic {

	public Map<String, List<Long>> processLogs(String path) throws IOException {
		SimpleDateFormat sdf = new SimpleDateFormat("HH:mm:ss");

		List<String> disList = new ArrayList<>();
		List<List<String>> orgList = new ArrayList<>();
		BufferedReader reader = new BufferedReader(new FileReader(path));
		List<String> lst = new ArrayList<>();
		lst = reader.lines().collect(Collectors.toList());
		for (String l : lst) {
			String[] val = l.split(" ");
			if (val.length == 3 && !disList.contains(val[1])) {
				disList.add(val[1]);
			}
			if (val.length == 3) {
				List<String> loc = Arrays.asList(val);
				orgList.add(loc);
			}
		}
		int len = orgList.size();
		Long logStartTime = 0l;
		Long logEndTime = 0l;
		try {
			logStartTime = sdf.parse(orgList.get(0).get(0)).getTime();
			logEndTime = sdf.parse(orgList.get(len - 1).get(0)).getTime();
		} catch (ParseException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		List<List<String>> dq1 = new ArrayList<>();
		Map<String, List<Long>> output = new HashMap<>();
		for (String s : disList) {
			Deque<List<String>> dq = new ArrayDeque<>();
			List<List<String>> kk = orgList.stream().filter(x -> x.get(1).equals(s)).collect(Collectors.toList());
			long totalTime = 0l;
			long count = 0l;
			for (List<String> k : kk) {
				try {
					if (k.get(2).equalsIgnoreCase("Start")) {
						dq.add(k);
					} else {
						if (!dq.isEmpty() && k.get(2).equalsIgnoreCase("End")
								&& dq.getFirst().get(2).equalsIgnoreCase("Start")) {

							count = count + 1;
							totalTime = totalTime + Math
									.abs(sdf.parse(k.get(0)).getTime() - sdf.parse(dq.getFirst().get(0)).getTime());
							dq.removeFirst();
						} else {
							count = count + 1;
							totalTime = totalTime + Math.abs(sdf.parse(k.get(0)).getTime() - logStartTime);
						}
					}
				} catch (ParseException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				List<Long> countTime = new ArrayList<>();
				countTime.add(count);
				countTime.add(totalTime);
				output.put(s, countTime);
			}
			dq1.addAll(dq);
		}

		for (List<String> l : dq1) {
			long time = 0l;
			long count = 0l;
			try {
				if (l.get(2).equalsIgnoreCase("Start")) {

					count = count + 1;
					time = Math.abs(sdf.parse(l.get(0)).getTime() - logEndTime);

				}
			} catch (ParseException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			List<Long> countTimes = new ArrayList<>();
			countTimes.add(output.get(l.get(1)).get(0) + count);
			countTimes.add(output.get(l.get(1)).get(1) + time);
			output.put(l.get(1), countTimes);
		}

		
		
		reader.close();
		return output;
	}
}
