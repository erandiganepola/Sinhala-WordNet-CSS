<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Edit Synset</title>
<link rel="stylesheet" type="text/css" href="theme/css/style.css">
<script type="text/javascript" src="theme/js/add_new_div.js"></script>
<script type="text/javascript" src="theme/js/expand_collapse.js"></script>
</head>
<body>
	<div id="wrap">
		<div id="new_sysnset">
			<div id="add_words" class="add_new">
				<div class="section_header">
					<h2>පද සම්බන්ධතා</h2>
				</div>
				<div id="words_div">
					<div id="word_1" class="word_set">
						<span id="ex_span_1" onclick="ExpandCollapse(this.id);"><img
							src="theme/images/collapse.png" /></span> <span id="show_span_1"
							class="show_span">සඟවන්න</span>
						<div id="ex_div_1" style="display: block;">
							<table class="word_table">
								<tbody>
									<tr>
										<td><label>සිංහල පදය</label></td>
										<td><input type="text" maxlength="255" size="22" /></td>
									</tr>
									<tr>
										<td><label>ප්‍රකෘතිය</label></td>
										<td><input type="text" maxlength="255" size="22"
											autocomplete="off" class="ui-autocomplete-input" /></td>
									</tr>
									<tr>
										<td><label>මූල භාෂාව</label></td>
										<td>
										  <select name="origine">
										      <option value="hindi">හින්දි</option>
										      <option value="tamil">දෙමළ</option>
										      <option value="english">ඉංග්‍රීසි</option>
										      <option value="portuguese">පෘතුග්‍රීසි</option>
										      <option value="dutch">ලංදේසි</option>
										  </select>
										</td>
									</tr>
									<tr>
                                        <td><label>මූල භාෂා වර්ගය</label></td>
                                        <td><input type="radio" name="derivation_type" value="thathsama" checked="checked">
                                            තත්සම <br /> <input type="radio" name="derivation_type"
                                            value="thathbawa"> තත්භව <br />
                                        </td>
                                    </tr>
                                    <tr>
                                        <td><label>භාවිතය</label></td>
                                        <td><input type="radio" name="usage" value="spoken" checked="checked">
                                            ලිඛිත <br /> <input type="radio" name="usage"
                                            value="written"> වාචික <br />
                                        </td>
                                    </tr>
									<tr>
										<td><label>විරුද්ධ පදය</label></td>
										<td><input type="text" maxlength="255" size="22"
											autocomplete="off" class="ui-autocomplete-input" /></td>
									</tr>
								</tbody>
							</table>
						</div>
					</div>
				</div>
				<div class="button_div">
					<input type="button" value="නව පදයක් යෙදීම" id="btn_add_word"
						class="button" onclick="copyDiv()" />
				</div>

				<div class="section_header">
					<h2>පද කුලකයේ සම්බන්ධතා</h2>
				</div>
				<div class="word_set">
					<table class="word_table">
						<tbody>
							<tr>
								<td><label>සිංහල අර්ථය :</label></td>
								<td><textarea rows="5" cols="30"></textarea></td>
							</tr>
							<tr>
								<td><label>සිංහල උදාහරණ :</label></td>
								<td><textarea rows="5" cols="30"></textarea></td>
							</tr>
							<tr>
                                        <td><label>ලිංග භේදය</label></td>
                                        <td><input type="radio" name="gender" value="male">
                                            පුරුෂ <br /> <input type="radio" name="gender"
                                            value="female"> ස්ත්‍රී <br /> <input type="radio"
                                            name="gender" value="neglect" checked="checked"> නොසලකා හරින්න <br />
                                        </td>
                                    </tr>
							<tr>
								<td><label>Hypernyms</label></td>
								<td><input type="text" maxlength="255" size="20" /></td>
							</tr>
							<tr>
								<td><label>Hyponyms</label></td>
								<td><input type="text" maxlength="255" size="20" /></td>
							</tr>
							<tr>
								<td colspan="2">
									<div class="sub_div">
										<div class="sub_div_header">
											<label>Meronyms</label>
										</div>
										<table>
											<tbody>
												<tr>
													<td><label>Made of</label></td>
													<td><input type="text" maxlength="255" size="21" /></td>
												</tr>
												<tr>
													<td><label>Component of</label></td>
													<td><input type="text" maxlength="255" size="21" /></td>
												</tr>
												<tr>
													<td><label>Member of</label></td>
													<td><input type="text" maxlength="255" size="21" /></td>
												</tr>
											</tbody>
										</table>
									</div>
								</td>

							</tr>
							<tr>
								<td colspan="2">
									<div class="sub_div">
										<div class="sub_div_header">
											<label>Holonyms</label>
										</div>
										<table>
											<tbody>
												<tr>
													<td><label>Made of</label></td>
													<td><input type="text" maxlength="255" size="21" /></td>
												</tr>
												<tr>
													<td><label>Component of</label></td>
													<td><input type="text" maxlength="255" size="21" /></td>
												</tr>
												<tr>
													<td><label>Member of</label></td>
													<td><input type="text" maxlength="255" size="21" /></td>
												</tr>
											</tbody>
										</table>
									</div>
								</td>
							</tr>
						</tbody>
					</table>
				</div>
				<div class="button_div">
					<input type="button" value="යොමන්න" id="btn_add_synset"
						class="button" style="float: right" />
				</div>
			</div>
			<div id="synset_relation" class="add_new">
				<div>
				<div class="word_set">
                    <div class="whitebox">
                        <h1>${synset.getWordsAsString()}</h1>
                    </div>
                    <div class="whitebox">
                        <table>
                            <tr>
                                <td valign="top">ඉංග්‍රීසි අර්ථය:</td>
                                <td valign="top">${synset.getDefinition()}</td>
                            </tr>
                            <tr>
                                <td valign="top">ඉංග්‍රීසි උදාහරණ:</td>
                                <td valign="top">${synset.getExample()}</td>
                            </tr>
                        </table>
                    </div>
                </div>
				</div>
				<div class="word_set">
					<center>
						<p>වචනවල පොදු අරුත්</p>
						<table width="238">
							<tr>
								<td width="125" valign="top"><p>${synset.getWordsAsString()}
										:</p></td>
								<td width="101" valign="top"><select id="list1" size="4"
									style="width: 100px" multiple="multiple"
									ondblclick="add2list(&#39;list1&#39;,&#39;synsetlist&#39;);">
										<c:forEach var="meaning" items="${intersection}">
											<option>${meaning}</option>
										</c:forEach>
								</select></td>
							</tr>
						</table>
					</center>
				</div>
				<div class="word_set">
					<center>
						<p>වචනවල අරුත්</p>
					</center>
					<table>
						<tr>
							<c:forEach begin="0" end="${wordList.size()-1}" varStatus="loop">
								<td width="" valign="top"><p>${wordList.get(loop.index)}
										:</p></td>
							</c:forEach>
						</tr>
						<tr>
							<c:forEach begin="0" end="${wordList.size()-1}" varStatus="loop">
								<td width="" valign="top"><select id="list2" size="10"
									style="width: 120px" multiple="multiple">
										<c:forEach var="meaning"
											items="${meaningsList.get(loop.index)}">
											<option>${meaning}</option>
										</c:forEach>
								</select></td>
							</c:forEach>
						</tr>
					</table>
				</div>
			</div>
		</div>
		<div class="footer">
			<div class="links">
				<p class="lintitle1">
					© සියලුම හිමිකම් ඇවිරිණි. <a href="http://www.uom.lk/"
						target="_new" class="cse">සිංහල Wordnet ව්‍යාපෘතිය.</a>
				</p>
			</div>
		</div>
	</div>
</body>
</html>
