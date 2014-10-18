package org.ripple.power.ui;

import java.awt.Color;
import java.awt.Dialog;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JDialog;
import javax.swing.JFrame;

import org.address.ripple.RippleAddress;
import org.json.JSONObject;
import org.ripple.power.config.LSystem;
import org.ripple.power.txns.AccountFind;
import org.ripple.power.txns.AccountInfo;
import org.ripple.power.txns.AccountLine;
import org.ripple.power.txns.IssuedCurrency;
import org.ripple.power.txns.Payment;
import org.ripple.power.txns.Rollback;
import org.ripple.power.txns.Updateable;
import org.ripple.power.wallet.WalletCache;
import org.ripple.power.wallet.WalletItem;

import com.ripple.core.coretypes.AccountID;

public class RPIOUSendDialog extends JDialog {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	// Variables declaration - do not modify
	private RPCButton _sendButton;
	private RPCButton _exitButton;
	private RPComboBox _curList;
	private RPLabel jLabel1;
	private RPLabel jLabel2;
	private RPLabel jLabel3;
	private RPLabel jLabel4;
	private javax.swing.JSeparator jSeparator1;
	private RPTextBox _feeText;
	private RPTextBox _addressText;
	private RPTextBox _amountText;

	// End of variables declaration

	private final WalletItem _walletItem;

	public static void showDialog(String name, JFrame parent, WalletItem item) {
		try {
			RPIOUSendDialog dialog = new RPIOUSendDialog(name, parent, item,
					"", "1", LSystem.FEE);
			dialog.pack();
			dialog.setLocationRelativeTo(parent);
			dialog.setVisible(true);
		} catch (Exception exc) {
			exc.printStackTrace();

		}
	}

	public static void showDialog(String name, JFrame parent, WalletItem item,
			String address, String amount, String fee) {
		try {
			RPIOUSendDialog dialog = new RPIOUSendDialog(name, parent, item,
					address, amount, fee);
			dialog.pack();
			dialog.setLocationRelativeTo(parent);
			dialog.setVisible(true);
		} catch (Exception exc) {
			exc.printStackTrace();

		}
	}

	public RPIOUSendDialog(String text, JFrame parent, final WalletItem item,
			String address, String amount, String fee) {
		super(parent, text, Dialog.ModalityType.DOCUMENT_MODAL);
		setResizable(false);
		Dimension dim = new Dimension(575, 270);
		setPreferredSize(dim);
		setSize(dim);

		_walletItem = item;

		getContentPane().setBackground(new Color(36, 36, 36));
		jLabel1 = new RPLabel();
		_curList = new RPComboBox();
		_sendButton = new RPCButton();
		jSeparator1 = new javax.swing.JSeparator();
		jLabel2 = new RPLabel();
		jLabel3 = new RPLabel();
		_feeText = new RPTextBox();
		_addressText = new RPTextBox();
		jLabel4 = new RPLabel();
		_amountText = new RPTextBox();
		_exitButton = new RPCButton();

		getContentPane().setLayout(null);

		jLabel1.setFont(new java.awt.Font("宋体", 0, 14)); // NOI18N
		jLabel1.setText("Fee");
		getContentPane().add(jLabel1);
		jLabel1.setBounds(10, 130, 70, 16);

		_curList.setFont(new java.awt.Font("宋体", 0, 14)); // NOI18N
		_curList.setModel(new javax.swing.DefaultComboBoxModel(
				new String[] { "Empty" }));
		getContentPane().add(_curList);
		_curList.setBounds(80, 10, 470, 21);

		_sendButton.setText("Send");
		getContentPane().add(_sendButton);
		_sendButton.setBounds(370, 200, 90, 30);
		_sendButton.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				String address = _addressText.getText().trim();
				String fee = _feeText.getText().trim();
				Object o = _curList.getSelectedItem();
				IssuedCurrency cur = null;
				if (o instanceof String) {
					cur = new IssuedCurrency((String) o);
				} else if (o instanceof IssuedCurrency) {
					cur = (IssuedCurrency) o;
				} else {
					RPMessage.showInfoMessage(LSystem.applicationMain, "Error",
							"发送失败,无法获得当前IOU货币数据!");
					return;
				}
				final WaitDialog dialog = WaitDialog
						.showDialog(RPIOUSendDialog.this);
				Payment.send(item.getSeed(), address, cur, fee, new Rollback() {

					@Override
					public void success(JSONObject res) {
						JSonLog.get().println(res.toString());
						WalletCache.get().reset();
						RPMessage.showInfoMessage(LSystem.applicationMain,
								"Info", "发送完毕.");
						dialog.closeDialog();
					}

					@Override
					public void error(JSONObject res) {
						JSonLog.get().println(res.toString());
						dialog.closeDialog();
					}
				});
			}
		});

		getContentPane().add(jSeparator1);
		jSeparator1.setBounds(0, 180, 570, 10);

		jLabel2.setFont(new java.awt.Font("宋体", 0, 14)); // NOI18N
		jLabel2.setText("Currency");
		getContentPane().add(jLabel2);
		jLabel2.setBounds(10, 10, 80, 16);

		jLabel3.setFont(new java.awt.Font("宋体", 0, 14)); // NOI18N
		jLabel3.setText("Address");
		getContentPane().add(jLabel3);
		jLabel3.setBounds(10, 50, 70, 16);

		_feeText.setFont(new java.awt.Font("宋体", 0, 14)); // NOI18N
		getContentPane().add(_feeText);
		_feeText.setBounds(80, 130, 160, 22);
		_feeText.setText(fee);

		_addressText.setFont(new java.awt.Font("宋体", 0, 14)); // NOI18N
		getContentPane().add(_addressText);
		_addressText.setBounds(80, 50, 470, 22);
		_addressText.setText(address);

		jLabel4.setFont(new java.awt.Font("宋体", 0, 14)); // NOI18N
		jLabel4.setText("Amount");
		getContentPane().add(jLabel4);
		jLabel4.setBounds(10, 90, 70, 16);

		_amountText.setFont(new java.awt.Font("宋体", 0, 14)); // NOI18N
		getContentPane().add(_amountText);
		_amountText.setBounds(80, 90, 300, 22);
		_amountText.setText(amount);

		_exitButton.setText("Exit");
		getContentPane().add(_exitButton);
		_exitButton.setBounds(470, 200, 90, 30);
		_exitButton.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				RPIOUSendDialog.this.setVisible(false);
				RPIOUSendDialog.this.dispose();
			}
		});
		calldisable();
		pack();
		loadIOUs(address);
	}

	public void calldisable() {
		_sendButton.setEnabled(false);
		_feeText.setEnabled(false);
		_addressText.setEnabled(false);
		_amountText.setEnabled(false);
	}

	public void callactivity() {
		_sendButton.setEnabled(true);
		_feeText.setEnabled(true);
		_addressText.setEnabled(true);
		_amountText.setEnabled(true);
	}

	private AccountInfo loadIOUs(String address) {
		final WaitDialog dialog = WaitDialog.showDialog(this);
		final AccountInfo info = new AccountInfo();
		Updateable accountline = new Updateable() {
			@Override
			public void action(Object res) {
				if (info.lines.size() > 0) {
					String[] list = new String[info.lines.size()];
					int index = 0;
					for (AccountLine line : info.lines) {
						list[index] = line.get().toString();
						index++;
					}
					_curList.setModel(new javax.swing.DefaultComboBoxModel(list));
					callactivity();
				} else {
					calldisable();
				}
				dialog.closeDialog();
			}
		};
		AccountFind find = new AccountFind();
		find.processLines(address, info, accountline);
		return info;
	}

}