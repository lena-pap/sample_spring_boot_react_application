import React, { Component } from 'react';
import { Button, ButtonGroup, Container, Table } from 'reactstrap';
import AppNavbar from './AppNavbar';
import { Link, withRouter } from 'react-router-dom';

class WalletList extends Component {

    constructor(props) {
        super(props);
        this.state = {wallets: []};
    }

    componentDidMount() {
        fetch('/wallets')
            .then(response => response.json())
            .then(data => this.setState({wallets: data}));
    }

    render() {
        const {wallets, isLoading} = this.state;
    
        if (isLoading) {
            return <p>Loading...</p>;
        }
    
        const walletList = wallets.map(wallet => {
            return <tr key={wallet.id}>
                <td style={{whiteSpace: 'nowrap'}}>{wallet.name}</td>
                <td>{wallet.amountInCents}</td>
                <td>
                    <ButtonGroup>
                        <Button size="sm" color="primary" tag={Link} to={"/balance/" + wallet.id}>Withdraw/Add</Button>
                    </ButtonGroup>
                </td>
            </tr>
        });
    
        return (
            <div>
                <AppNavbar/>
                <Container fluid>
                    <div className="float-right">
                        <Button color="success" tag={Link} to="/wallets/new">Add Wallet</Button>
                    </div>
                    <h3>Wallets</h3>
                    <Table className="mt-4">
                        <thead>
                        <tr>
                            <th width="30%">Name</th>
                            <th width="30%">Amount in cents</th>
                            <th width="40%">Actions</th>
                        </tr>
                        </thead>
                        <tbody>
                        {walletList}
                        </tbody>
                    </Table>
                </Container>
            </div>
        );
    }
}
export default withRouter(WalletList);