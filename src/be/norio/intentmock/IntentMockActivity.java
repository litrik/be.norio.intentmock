/**
 *	Copyright 2011 Norio bvba
 *
 *	This program is free software: you can redistribute it and/or modify
 *	it under the terms of the GNU General Public License as published by
 *	the Free Software Foundation, either version 3 of the License, or
 *	(at your option) any later version.
 *	
 *	This program is distributed in the hope that it will be useful,
 *	but WITHOUT ANY WARRANTY; without even the implied warranty of
 *	MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 *	GNU General Public License for more details.
 *	
 *	You should have received a copy of the GNU General Public License
 *	along with this program.  If not, see <http://www.gnu.org/licenses/>.
 */

package be.norio.intentmock;

import java.lang.reflect.Array;
import java.util.Set;

import android.app.Activity;
import android.os.Bundle;
import android.widget.TextView;

public class IntentMockActivity extends Activity {

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.main);
	}

	@Override
	protected void onResume() {
		super.onResume();
		TextView tv = (TextView) findViewById(R.id.dump);
		Bundle b = getIntent().getExtras();
		Set<String> keys = b.keySet();
		StringBuffer sb = new StringBuffer();
		for (String key : keys) {
			sb.append(key);
			sb.append("=");
			if (b.get(key) instanceof String) {
				sb.append(b.getString(key));
			} else if (b.get(key).getClass().isArray()) {
				for (int i = 0; i < Array.getLength(b.get(key)); i++) {
					sb.append(Array.get(b.get(key), i));
					sb.append(",");
				}
			} else {
				sb.append(b.get(key));
			}
			sb.append("\n");
		}
		tv.setText(sb);
	}
}