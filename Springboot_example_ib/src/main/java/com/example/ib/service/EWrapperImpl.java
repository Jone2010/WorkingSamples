package com.example.ib.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

import com.example.ib.IBMarketData;
import com.ib.client.Bar;
import com.ib.client.CommissionReport;
import com.ib.client.Contract;
import com.ib.client.ContractDescription;
import com.ib.client.ContractDetails;
import com.ib.client.DeltaNeutralContract;
import com.ib.client.DepthMktDataDescription;
import com.ib.client.EClientSocket;
import com.ib.client.EJavaSignal;
import com.ib.client.EReader;
import com.ib.client.EWrapper;
import com.ib.client.Execution;
import com.ib.client.FamilyCode;
import com.ib.client.HistogramEntry;
import com.ib.client.HistoricalTick;
import com.ib.client.HistoricalTickBidAsk;
import com.ib.client.HistoricalTickLast;
import com.ib.client.NewsProvider;
import com.ib.client.Order;
import com.ib.client.OrderState;
import com.ib.client.PriceIncrement;
import com.ib.client.SoftDollarTier;
import com.ib.client.TickAttrib;
import com.ib.client.TickAttribBidAsk;
import com.ib.client.TickAttribLast;
import com.ib.client.TickType;

public class EWrapperImpl implements EWrapper {

	private EJavaSignal clientSignal = new EJavaSignal();
	private final EClientSocket clientSocket = new EClientSocket(this, clientSignal);

	private int nextId; // auto increment for each request
	private int myId;
	private Contract contract = new Contract();
	private EReader reader = null;

	private String twsAccountNumber = "999"; // "999" 888;

	private List<IBMarketData> ibMarketData = null;

	public EWrapperImpl() {
		this.setIbMarketData(new ArrayList<IBMarketData>());
		this.nextId = 1;
	}

	public void requestMarketDataFromTWS() {
		clientSocket.eConnect(null, 7497, 0);

		reader = new EReader(clientSocket, clientSignal);
		reader.start();

		while (clientSocket.isConnected()) {
			clientSignal.waitForSignal();
			try {
				reader.processMsgs();
				clientSocket.reqMarketDataType(3);

				contract.symbol("EUR");
				contract.exchange("IDEALPRO");
				contract.secType("CASH");
				contract.currency("USD");

				this.myId = this.nextId++;
				System.out.println("requestStreamingData is being called");
				clientSocket.reqMktData(this.myId, contract, "", true, false, null);
				System.out.println("requestStreamingData is finished");

				clientSocket.reqAccountUpdates(true, twsAccountNumber);
			} catch (Exception e) {
				error(e);
			}
		}

	}

	public void historicalData(int reqId, String date, double open, double high, double low, double close, int volume,
			int count, double WAP, boolean hasGaps) {

	}

	@Override
	public void accountDownloadEnd(String arg0) {
		// TODO Auto-generated method stub

	}

	@Override
	public void accountSummary(int arg0, String arg1, String arg2, String arg3, String arg4) {
		// TODO Auto-generated method stub

	}

	@Override
	public void accountSummaryEnd(int arg0) {
		// TODO Auto-generated method stub

	}

	@Override
	public void accountUpdateMulti(int arg0, String arg1, String arg2, String arg3, String arg4, String arg5) {
		// TODO Auto-generated method stub

	}

	@Override
	public void accountUpdateMultiEnd(int arg0) {
		// TODO Auto-generated method stub

	}

	@Override
	public void bondContractDetails(int arg0, ContractDetails arg1) {
		// TODO Auto-generated method stub

	}

	@Override
	public void commissionReport(CommissionReport arg0) {
		// TODO Auto-generated method stub

	}

	@Override
	public void completedOrdersEnd() {
		// TODO Auto-generated method stub

	}

	@Override
	public void connectAck() {
		// TODO Auto-generated method stub

	}

	@Override
	public void connectionClosed() {
		// TODO Auto-generated method stub

	}

	@Override
	public void contractDetails(int arg0, ContractDetails arg1) {
		// TODO Auto-generated method stub

	}

	@Override
	public void contractDetailsEnd(int arg0) {
		// TODO Auto-generated method stub

	}

	@Override
	public void currentTime(long arg0) {
		// TODO Auto-generated method stub

	}

	@Override
	public void deltaNeutralValidation(int arg0, DeltaNeutralContract arg1) {
		// TODO Auto-generated method stub

	}

	@Override
	public void displayGroupList(int arg0, String arg1) {
		// TODO Auto-generated method stub

	}

	@Override
	public void displayGroupUpdated(int arg0, String arg1) {
		// TODO Auto-generated method stub

	}

	@Override
	public void error(Exception arg0) {
		// TODO Auto-generated method stub

	}

	@Override
	public void error(String arg0) {
		// TODO Auto-generated method stub

	}

	@Override
	public void error(int arg0, int arg1, String arg2) {
		// TODO Auto-generated method stub

	}

	@Override
	public void execDetails(int arg0, Contract arg1, Execution arg2) {
		// TODO Auto-generated method stub

	}

	@Override
	public void execDetailsEnd(int arg0) {
		// TODO Auto-generated method stub

	}

	@Override
	public void familyCodes(FamilyCode[] arg0) {
		// TODO Auto-generated method stub

	}

	@Override
	public void fundamentalData(int arg0, String arg1) {
		// TODO Auto-generated method stub

	}

	@Override
	public void headTimestamp(int arg0, String arg1) {
		// TODO Auto-generated method stub

	}

	@Override
	public void histogramData(int arg0, List<HistogramEntry> arg1) {
		// TODO Auto-generated method stub

	}

	@Override
	public void historicalData(int arg0, Bar arg1) {
		// TODO Auto-generated method stub

	}

	@Override
	public void historicalDataEnd(int arg0, String arg1, String arg2) {
		// TODO Auto-generated method stub

	}

	@Override
	public void historicalDataUpdate(int arg0, Bar arg1) {
		// TODO Auto-generated method stub

	}

	@Override
	public void historicalNews(int arg0, String arg1, String arg2, String arg3, String arg4) {
		// TODO Auto-generated method stub

	}

	@Override
	public void historicalNewsEnd(int arg0, boolean arg1) {
		// TODO Auto-generated method stub

	}

	@Override
	public void historicalTicks(int arg0, List<HistoricalTick> arg1, boolean arg2) {
		// TODO Auto-generated method stub

	}

	@Override
	public void historicalTicksBidAsk(int arg0, List<HistoricalTickBidAsk> arg1, boolean arg2) {
		// TODO Auto-generated method stub

	}

	@Override
	public void historicalTicksLast(int arg0, List<HistoricalTickLast> arg1, boolean arg2) {
		// TODO Auto-generated method stub

	}

	@Override
	public void managedAccounts(String arg0) {
		// TODO Auto-generated method stub

	}

	@Override
	public void marketDataType(int arg0, int arg1) {
		// TODO Auto-generated method stub

	}

	@Override
	public void marketRule(int arg0, PriceIncrement[] arg1) {
		// TODO Auto-generated method stub

	}

	@Override
	public void mktDepthExchanges(DepthMktDataDescription[] arg0) {
		// TODO Auto-generated method stub

	}

	@Override
	public void newsArticle(int arg0, int arg1, String arg2) {
		// TODO Auto-generated method stub

	}

	@Override
	public void newsProviders(NewsProvider[] arg0) {
		// TODO Auto-generated method stub

	}

	@Override
	public void nextValidId(int orderId) {
		// TODO Auto-generated method stub
	}

	@Override
	public void openOrderEnd() {
		// TODO Auto-generated method stub

	}

	@Override
	public void orderBound(long arg0, int arg1, int arg2) {
		// TODO Auto-generated method stub

	}

	@Override
	public void orderStatus(int arg0, String arg1, double arg2, double arg3, double arg4, int arg5, int arg6,
			double arg7, int arg8, String arg9, double arg10) {
		// TODO Auto-generated method stub

	}

	@Override
	public void pnl(int arg0, double arg1, double arg2, double arg3) {
		// TODO Auto-generated method stub

	}

	@Override
	public void pnlSingle(int arg0, int arg1, double arg2, double arg3, double arg4, double arg5) {
		// TODO Auto-generated method stub

	}

	@Override
	public void position(String arg0, Contract arg1, double arg2, double arg3) {
		// TODO Auto-generated method stub

	}

	@Override
	public void positionEnd() {
		// TODO Auto-generated method stub

	}

	@Override
	public void positionMulti(int arg0, String arg1, String arg2, Contract arg3, double arg4, double arg5) {
		// TODO Auto-generated method stub

	}

	@Override
	public void positionMultiEnd(int arg0) {
		// TODO Auto-generated method stub

	}

	@Override
	public void realtimeBar(int arg0, long arg1, double arg2, double arg3, double arg4, double arg5, long arg6,
			double arg7, int arg8) {
		// TODO Auto-generated method stub

	}

	@Override
	public void receiveFA(int arg0, String arg1) {
		// TODO Auto-generated method stub

	}

	@Override
	public void replaceFAEnd(int arg0, String arg1) {
		// TODO Auto-generated method stub

	}

	@Override
	public void rerouteMktDataReq(int arg0, int arg1, String arg2) {
		// TODO Auto-generated method stub

	}

	@Override
	public void rerouteMktDepthReq(int arg0, int arg1, String arg2) {
		// TODO Auto-generated method stub

	}

	@Override
	public void scannerData(int arg0, int arg1, ContractDetails arg2, String arg3, String arg4, String arg5,
			String arg6) {
		// TODO Auto-generated method stub

	}

	@Override
	public void scannerDataEnd(int arg0) {
		// TODO Auto-generated method stub

	}

	@Override
	public void scannerParameters(String arg0) {
		// TODO Auto-generated method stub

	}

	@Override
	public void securityDefinitionOptionalParameter(int arg0, String arg1, int arg2, String arg3, String arg4,
			Set<String> arg5, Set<Double> arg6) {
		// TODO Auto-generated method stub

	}

	@Override
	public void securityDefinitionOptionalParameterEnd(int arg0) {
		// TODO Auto-generated method stub

	}

	@Override
	public void smartComponents(int arg0, Map<Integer, Entry<String, Character>> arg1) {
		// TODO Auto-generated method stub

	}

	@Override
	public void softDollarTiers(int arg0, SoftDollarTier[] arg1) {
		// TODO Auto-generated method stub

	}

	@Override
	public void symbolSamples(int arg0, ContractDescription[] arg1) {
		// TODO Auto-generated method stub

	}

	@Override
	public void tickByTickAllLast(int arg0, int arg1, long arg2, double arg3, int arg4, TickAttribLast arg5,
			String arg6, String arg7) {
		// TODO Auto-generated method stub

	}

	@Override
	public void tickByTickBidAsk(int arg0, long arg1, double arg2, double arg3, int arg4, int arg5,
			TickAttribBidAsk arg6) {
		// TODO Auto-generated method stub

	}

	@Override
	public void tickByTickMidPoint(int arg0, long arg1, double arg2) {
		// TODO Auto-generated method stub

	}

	@Override
	public void tickEFP(int arg0, int arg1, double arg2, String arg3, double arg4, int arg5, String arg6, double arg7,
			double arg8) {
		// TODO Auto-generated method stub

	}

	@Override
	public void tickGeneric(int arg0, int arg1, double arg2) {
		// TODO Auto-generated method stub

	}

	@Override
	public void tickNews(int arg0, long arg1, String arg2, String arg3, String arg4, String arg5) {
		// TODO Auto-generated method stub

	}

	@Override
	public void tickOptionComputation(int arg0, int arg1, int arg2, double arg3, double arg4, double arg5, double arg6,
			double arg7, double arg8, double arg9, double arg10) {
		// TODO Auto-generated method stub

	}

	/*
	 * public void tickPrice(int orderId, int field, double price, int
	 * canAutoExecute) { try { // Print out the current price. // field will provide
	 * the price type: // 1 = bid, 2 = ask, 4 = last // 6 = high, 7 = low, 9 = close
	 * System.out.println("tickPrice: " + field + "," + price); } catch (Exception
	 * e) { e.printStackTrace (); } } // end tickPrice
	 */
	@Override
	public void tickReqParams(int arg0, double arg1, String arg2, int arg3) {
		// TODO Auto-generated method stub

	}

	@Override
	public void tickSize(int arg0, int arg1, int arg2) {
		// TODO Auto-generated method stub

	}

	@Override
	public void tickSnapshotEnd(int arg0) {
		// TODO Auto-generated method stub

	}

	@Override
	public void tickString(int arg0, int arg1, String arg2) {
		// TODO Auto-generated method stub

	}

	@Override
	public void updateAccountTime(String arg0) {
		// TODO Auto-generated method stub

	}

	@Override
	public void updateAccountValue(String arg0, String arg1, String arg2, String arg3) {
		// TODO Auto-generated method stub

	}

	@Override
	public void updateMktDepth(int arg0, int arg1, int arg2, int arg3, double arg4, int arg5) {
		// TODO Auto-generated method stub

	}

	@Override
	public void updateMktDepthL2(int arg0, int arg1, String arg2, int arg3, int arg4, double arg5, int arg6,
			boolean arg7) {
		// TODO Auto-generated method stub

	}

	@Override
	public void updateNewsBulletin(int arg0, int arg1, String arg2, String arg3) {
		// TODO Auto-generated method stub

	}

	@Override
	public void updatePortfolio(Contract arg0, double arg1, double arg2, double arg3, double arg4, double arg5,
			double arg6, String arg7) {
		// TODO Auto-generated method stub

	}

	@Override
	public void verifyAndAuthCompleted(boolean arg0, String arg1) {
		// TODO Auto-generated method stub

	}

	@Override
	public void verifyAndAuthMessageAPI(String arg0, String arg1) {
		// TODO Auto-generated method stub

	}

	@Override
	public void verifyCompleted(boolean arg0, String arg1) {
		// TODO Auto-generated method stub

	}

	@Override
	public void verifyMessageAPI(String arg0) {
		// TODO Auto-generated method stub

	}

	@Override
	public void tickPrice(int tickerId, int field, double price, TickAttrib attrib) {
		IBMarketData ibMarketData = new IBMarketData(tickerId, TickType.getField(field), price, attrib.toString());
		this.getIbMarketData().add(ibMarketData);
		System.out.println("id: " + tickerId + " " + TickType.getField(field) + " price: " + price);
	}

	@Override
	public void openOrder(int orderId, Contract contract, Order order, OrderState orderState) {
		// TODO Auto-generated method stub

	}

	@Override
	public void completedOrder(Contract contract, Order order, OrderState orderState) {
		// TODO Auto-generated method stub

	}

	public List<IBMarketData> getIbMarketData() {
		if (null == this.ibMarketData) {
			return new ArrayList<IBMarketData>();
		}
		return ibMarketData;
	}

	public void setIbMarketData(List<IBMarketData> ibMarketData) {
		this.ibMarketData = ibMarketData;
	}

}