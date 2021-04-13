package com.hackerrank.practice;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

public class Crossword {

	private static class Coordinates implements Comparable<Coordinates> {
		Integer xAxis;
		Integer yAxis;

		public Coordinates(int xAxis, int yAxis) {
			this.xAxis = xAxis;
			this.yAxis = yAxis;
		}

		@Override
		public boolean equals(Object o) {
			Coordinates object = (Coordinates) o;
			return (this.xAxis == object.xAxis) && (this.yAxis == object.yAxis);

		}

		@Override
		public String toString() {
			return "X- Axis : " + this.xAxis + " Y-Axis : " + this.yAxis;
		}

		@Override
		public int hashCode() {
			return this.xAxis * this.yAxis;
		}

		@Override
		public int compareTo(Coordinates o) {
			// TODO Auto-generated method stub
			return this.yAxis.compareTo(o.yAxis);
		}
	}

	// Complete the crosswordPuzzle function below.
	static String[] crosswordPuzzle(String[] crossword, String words) {
		String[] wordsArray = words.split(";");
		Map<Integer, List<String>> wordsMap = getWordsMap(wordsArray);
		Map<Integer, LinkedList<Coordinates>> horizontalMap = findHorizontalPlaceHolders(crossword);
		char[][] crossWordCharArray = getCrosswordCharArray(crossword);
		Map<Integer, LinkedList<Coordinates>> verticalMap = findVerticalPlaceHolders(crossWordCharArray);

		String[] crosswordArranged = new String[10];

		// find the words which have unique length
		List<Integer> wordsMapKeysToBeDeleted = new ArrayList<>();
		arrangeUniqueLengthWords(wordsMap, horizontalMap, verticalMap, crossWordCharArray, wordsMapKeysToBeDeleted);
		wordsMapKeysToBeDeleted.forEach(e -> wordsMap.remove(e));
		wordsMapKeysToBeDeleted.forEach(e -> horizontalMap.remove(e));
		wordsMapKeysToBeDeleted.forEach(e -> verticalMap.remove(e));
		wordsMapKeysToBeDeleted.clear();
		int totalWords = wordsMap.values().stream().mapToInt(List::size).sum();
		System.out.println("Total Words : " + totalWords);
		arrangeRowWords(wordsMap, horizontalMap, crossWordCharArray, wordsMapKeysToBeDeleted);
		arrangeColumnWords(wordsMap, verticalMap, crossWordCharArray, wordsMapKeysToBeDeleted);
		int intermediateWordsCount = wordsMap.values().stream().mapToInt(List::size).sum();
		System.out.println("Intermediate Words : " + intermediateWordsCount);
		while (intermediateWordsCount < totalWords) {
			totalWords = intermediateWordsCount;
			arrangeRowWords(wordsMap, horizontalMap, crossWordCharArray, wordsMapKeysToBeDeleted);
			arrangeColumnWords(wordsMap, verticalMap, crossWordCharArray, wordsMapKeysToBeDeleted);
			intermediateWordsCount = wordsMap.values().stream().mapToInt(List::size).sum();
			System.out.println("Intermediate Words : " + intermediateWordsCount);
		}
		totalWords = wordsMap.values().stream().mapToInt(List::size).sum();
		System.out.println("Total Words : " + totalWords);
		findIntersectingWords(wordsMap, horizontalMap, verticalMap, crossWordCharArray, wordsMapKeysToBeDeleted);
		intermediateWordsCount = wordsMap.values().stream().mapToInt(List::size).sum();
		System.out.println("Intermediate Words : " + intermediateWordsCount);
		for (int i = 0; i < 10; i++) {
			crosswordArranged[i] = new String(crossWordCharArray[i]);
		}
		return crosswordArranged;
	}

	private static void findIntersectingWords(Map<Integer, List<String>> wordsMap,
			Map<Integer, LinkedList<Coordinates>> horizontalMap, Map<Integer, LinkedList<Coordinates>> verticalMap,
			char[][] crossWordCharArray, List<Integer> wordsMapKeysToBeDeleted) {
		for (Integer length : horizontalMap.keySet()) {
			if (verticalMap.get(length) != null) {
				for (Coordinates horizontalCoordinates : horizontalMap.get(length)) {
					for (Coordinates verticalCoordinates : verticalMap.get(length)) {
						System.out.println("--------------------------------------");
						List<Coordinates> intersectingCoordinates = new ArrayList<>();
						Set<Coordinates> coordinateSet = new HashSet<>();
						for (int i = 0; i < length; i++) {
							Coordinates hTemp = new Coordinates(horizontalCoordinates.xAxis + i,
									horizontalCoordinates.yAxis);
							Coordinates vTemp = new Coordinates(verticalCoordinates.xAxis,
									verticalCoordinates.yAxis + i);
							if (!coordinateSet.add(hTemp)) {
								intersectingCoordinates.add(hTemp);
							}
							if (!coordinateSet.add(vTemp)) {
								intersectingCoordinates.add(vTemp);
							}
						}
						System.out.println("--------------------------------------");
						if (intersectingCoordinates.size() != 0) {
							System.out.println(intersectingCoordinates);
							Coordinates intersectionPoint = intersectingCoordinates.get(0);
							int distanceFromRow = intersectionPoint.xAxis - horizontalCoordinates.xAxis;
							int distanceFromCol = intersectionPoint.yAxis - verticalCoordinates.yAxis;
							List<String> availableWords = wordsMap.get(length);
							List<String> filteredWords = new ArrayList<>();
							filteredWords = getRowFilteredWords(length, filteredWords, wordsMap, horizontalCoordinates,
									crossWordCharArray, wordsMapKeysToBeDeleted);
							filteredWords = getColumnFilteredWords(length, filteredWords, wordsMap, verticalCoordinates,
									crossWordCharArray, wordsMapKeysToBeDeleted);
							String rowWord = null;
							String columnWord = null;
							for (String filteredWord : filteredWords) {
								for (String word : filteredWords) {
									if (filteredWord.charAt(distanceFromRow) == word.charAt(distanceFromCol)) {
										rowWord = filteredWord;
										columnWord = word;
										break;
									}
									if (word.charAt(distanceFromRow) == filteredWord.charAt(distanceFromCol)) {
										rowWord = word;
										columnWord = filteredWord;
										break;
									}
								}
								if (rowWord != null)
									break;
							}
							insertRowWord(crossWordCharArray, horizontalCoordinates, rowWord);
							wordsMap.get(length).remove(rowWord);
							insertColumnWord(crossWordCharArray, verticalCoordinates, columnWord);
							wordsMap.get(length).remove(rowWord);
						}
					}

				}
			}
		}

	}

	private static void arrangeRowWords(Map<Integer, List<String>> wordsMap,
			Map<Integer, LinkedList<Coordinates>> horizontalMap, char[][] crossWordCharArray,
			List<Integer> wordsMapKeysToBeDeleted) {
		for (Integer length : horizontalMap.keySet()) {
			List<Coordinates> coordinatesToBeDeleted = new ArrayList<>();
			for (Coordinates coordinates : horizontalMap.get(length)) {
				List<String> filteredWords = new ArrayList<>();
				filteredWords = getRowFilteredWords(length, filteredWords, wordsMap, coordinates, crossWordCharArray,
						wordsMapKeysToBeDeleted);
				if (filteredWords.size() == 1) {
					String filteredWord = filteredWords.get(0);
					for (int i = 0; i < length; i++) {
						crossWordCharArray[coordinates.yAxis][coordinates.xAxis + i] = filteredWord.charAt(i);
					}
					coordinatesToBeDeleted.add(coordinates);
					wordsMap.get(length).remove(filteredWord);
				}
			}
			horizontalMap.get(length).removeAll(coordinatesToBeDeleted);
		}
	}

	private static void arrangeColumnWords(Map<Integer, List<String>> wordsMap,
			Map<Integer, LinkedList<Coordinates>> verticalMap, char[][] crossWordCharArray,
			List<Integer> wordsMapKeysToBeDeleted) {
		for (Integer length : verticalMap.keySet()) {
			List<Coordinates> coordinatesToBeDeleted = new ArrayList<>();
			for (Coordinates coordinates : verticalMap.get(length)) {
				List<String> filteredWords = new ArrayList<>();
				filteredWords = getColumnFilteredWords(length, filteredWords, wordsMap, coordinates, crossWordCharArray,
						wordsMapKeysToBeDeleted);
				if (filteredWords.size() == 1) {
					String filteredWord = filteredWords.get(0);
					for (int i = 0; i < length; i++) {
						crossWordCharArray[coordinates.yAxis + i][coordinates.xAxis] = filteredWord.charAt(i);
					}
					coordinatesToBeDeleted.add(coordinates);
					wordsMap.get(length).remove(filteredWord);
				}
			}
			verticalMap.get(length).removeAll(coordinatesToBeDeleted);
		}

	}

	private static List<String> getRowFilteredWords(int length, List<String> filteredWords,
			Map<Integer, List<String>> wordsMap, Coordinates coordinates, char[][] crossWordCharArray,
			List<Integer> wordsMapKeysToBeDeleted) {
		for (int i = 0; i < length; i++) {
			char charAtI = crossWordCharArray[coordinates.yAxis][coordinates.xAxis + i];
			if (charAtI != '-') {
				int j = i;
				if (filteredWords.size() == 0)
					filteredWords = wordsMap.get(length).stream().filter(e -> e.charAt(j) == charAtI)
							.collect(Collectors.toList());
				else
					filteredWords = filteredWords.stream().filter(e -> e.charAt(j) == charAtI)
							.collect(Collectors.toList());
			}
		}
		return filteredWords;
	}

	private static List<String> getColumnFilteredWords(int length, List<String> filteredWords,
			Map<Integer, List<String>> wordsMap, Coordinates coordinates, char[][] crossWordCharArray,
			List<Integer> wordsMapKeysToBeDeleted) {
		for (int i = 0; i < length; i++) {
			char charAtI = crossWordCharArray[coordinates.yAxis + i][coordinates.xAxis];
			if (charAtI != '-') {
				int j = i;
				if (filteredWords.size() == 0)
					filteredWords = wordsMap.get(length).stream().filter(e -> e.charAt(j) == charAtI)
							.collect(Collectors.toList());
				else
					filteredWords = filteredWords.stream().filter(e -> e.charAt(j) == charAtI)
							.collect(Collectors.toList());
			}
		}
		return filteredWords;
	}

	private static void arrangeUniqueLengthWords(Map<Integer, List<String>> wordsMap,
			Map<Integer, LinkedList<Coordinates>> horizontalMap, Map<Integer, LinkedList<Coordinates>> verticalMap,
			char[][] crossWordCharArray, List<Integer> wordsMapKeysToBeDeleted) {
		for (Integer key : wordsMap.keySet()) {
			List<String> wordsList = wordsMap.get(key);
			if (wordsList.size() == 1) {
				String word = wordsList.get(0);
				if (horizontalMap.get(key) != null) {
					// insert the word in row
					Coordinates coordinates = horizontalMap.get(key).getFirst();
					insertRowWord(crossWordCharArray, coordinates, word);
				} else if (verticalMap.get(key) != null) {
					// insert the row in column
					Coordinates coordinates = verticalMap.get(key).getFirst();
					insertColumnWord(crossWordCharArray, coordinates, word);
				}
				wordsMapKeysToBeDeleted.add(key);
			}
		}
	}

	private static void insertPlaceHolders(Map<Integer, LinkedList<Coordinates>> placeHolderMap, Coordinates coordinate,
			int length) {
		LinkedList<Coordinates> coordinateList;
		if (placeHolderMap.get(length) == null) {
			coordinateList = new LinkedList<Coordinates>();
			placeHolderMap.put(length, coordinateList);
		} else {
			coordinateList = placeHolderMap.get(length);
		}
		coordinateList.add(coordinate);
	}

	// Working Correctly
	private static void insertRowWord(char[][] crossWordCharArray, Coordinates coordinates, String word) {
		int xAxis = coordinates.xAxis;
		int yAxis = coordinates.yAxis;
		for (int i = 0; i < word.length(); i++) {
			crossWordCharArray[yAxis][xAxis + i] = word.charAt(i);
		}
	}

	// Working Correctly
	private static void insertColumnWord(char[][] crossWordCharArray, Coordinates coordinates, String word) {
		int xAxis = coordinates.xAxis;
		int yAxis = coordinates.yAxis;
		for (int i = 0; i < word.length(); i++) {
			crossWordCharArray[yAxis + i][xAxis] = word.charAt(i);
		}
	}

	// Working Correctly
	private static Map<Integer, LinkedList<Coordinates>> findVerticalPlaceHolders(char[][] crossWordCharArray) {
		Map<Integer, LinkedList<Coordinates>> verticalMap = new HashMap<>();
		Pattern emptyGridPattern = Pattern.compile("-{2,}");
		for (int m = 0; m < 10; m++) {
			StringBuffer strBuffer = new StringBuffer();
			for (int n = 0; n < 10; n++) {
				strBuffer.append(crossWordCharArray[n][m]);
			}
			String temp = strBuffer.toString();
			Matcher verticalMatcher = emptyGridPattern.matcher(temp);
			int i = 0;
			while (verticalMatcher.find()) {
				insertPlaceHolders(verticalMap, new Coordinates(m, verticalMatcher.start()),
						verticalMatcher.group(i).length());
				i++;
			}
		}
		return verticalMap;
	}

	private static char[][] getCrosswordCharArray(String[] crossword) {
		char[][] crossWordCharArray = new char[10][10];
		int j = 0;
		for (String str : crossword) {
			crossWordCharArray[j] = str.toCharArray();
			j++;
		}
		return crossWordCharArray;
	}

	// Working correctly
	private static Map<Integer, LinkedList<Coordinates>> findHorizontalPlaceHolders(String[] crossword) {
		Map<Integer, LinkedList<Coordinates>> horizontalMap = new HashMap<>();
		Pattern emptyGridPattern = Pattern.compile("-{2,}");
		int i = 0;
		for (String str : crossword) {
			Matcher horizontalMatcher = emptyGridPattern.matcher(str);
			int j = 0;
			while (horizontalMatcher.find()) {
				insertPlaceHolders(horizontalMap, new Coordinates(horizontalMatcher.start(), i),
						horizontalMatcher.group(j).length());
				j++;
			}
			i++;
		}
		return horizontalMap;
	}

	private static Map<Integer, List<String>> getWordsMap(String[] wordsArray) {
		Map<Integer, List<String>> wordsMap = new HashMap<>();
		for (String word : wordsArray) {
			if (wordsMap.get(word.length()) != null) {
				wordsMap.get(word.length()).add(word);
			} else {
				List<String> wordList = new ArrayList<>();
				wordList.add(word);
				wordsMap.put(word.length(), wordList);
			}
		}
		return wordsMap;
	}

	public static void main(String[] args) throws IOException {
		String[] crossword = new String[10];

		crossword[0] = "+-++++++++";
		crossword[1] = "+-++++++++";
		crossword[2] = "+-------++";
		crossword[3] = "+-++++++++";
		crossword[4] = "+-++++++++";
		crossword[5] = "+------+++";
		crossword[6] = "+-+++-++++";
		crossword[7] = "+++++-++++";
		crossword[8] = "+++++-++++";
		crossword[9] = "++++++++++";
		// String words = "AGRA;NORWAY;ENGLAND;GWALIOR";

		crossword[0] = "++++++-+++";
		crossword[1] = "++------++";
		crossword[2] = "++++++-+++";
		crossword[3] = "++++++-+++";
		crossword[4] = "+++------+";
		crossword[5] = "++++++-+-+";
		crossword[6] = "++++++-+-+";
		crossword[7] = "++++++++-+";
		crossword[8] = "++++++++-+";
		crossword[9] = "++++++++-+";
		String words = "ICELAND;MEXICO;PANAMA;ALMATY";

		String[] result = crosswordPuzzle(crossword, words);

		for (int i = 0; i < result.length; i++) {
			System.out.println(result[i]);

		}

	}
}
